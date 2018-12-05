package com.projects.appversion;

import java.util.Arrays;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class VersionRetriever {
    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected TextEncryptor textEncryptor;

    private final static Logger log = LoggerFactory.getLogger(VersionRetriever.class.getName());

    protected String NOTAVAILABLE = "<b>HOST NOT AVAILABLE</b>";

    protected String APIKEY = "api_key";

    @Async
    public Future<String> getAsyncVersionValueBody(String... urlAndheader) {

        String blockValue = "";
        String LINEBREAK = "<br>";
        String headersVal=null, uri=null;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        if (urlAndheader.length == 1) {
            uri=urlAndheader[0];
        } else if (urlAndheader.length == 2) {
            uri = urlAndheader[0];
            headersVal=urlAndheader[1];
            
            if (!headersVal.isEmpty()) {
                headers.remove(APIKEY);
                headers.add(APIKEY, headersVal.isEmpty() ? "" : textEncryptor.decrypt(headersVal));
            }
        }


        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        try {
//            log.info("*************Url: " + uri + " - Headers: " + headers);
            if (uri.isEmpty()) {
                blockValue = NOTAVAILABLE;
            } else {
                ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
                String versionDate = result.getBody().replace(",", ",<br>").replace("{", "").replace("}", "");
                blockValue = uri + LINEBREAK + versionDate;
            }
        } catch (RestClientException restex) {
            log.error("ERROR when making request: " + restex + " url: " + uri);
            //blockValue = uri + LINEBREAK + "<b>" + restex.getMessage() + "</b>";
            blockValue = uri + LINEBREAK + "<b>" + getShortError(restex.getMessage()) + "</b>";
        }
        return new AsyncResult<String>(blockValue);
    }
    
    private String getShortError(String longError) {
        String shortError = "";
        if(longError.contains("connect timed out")) {
            shortError = "Error connection timed out";
        } else {
            shortError = longError;
        }
        return shortError;
    }
}

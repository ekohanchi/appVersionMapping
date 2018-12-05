package com.projects.appversion.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.web.client.RestTemplate;

import com.projects.appversion.ApiConfig;
import com.projects.appversion.VersionRetriever;

public class BaseController {
    private final static Logger log = LoggerFactory.getLogger(BaseController.class.getName());

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected ApiConfig apiConfig;

    @Autowired
    protected TextEncryptor textEncryptor;

    @Autowired
    protected VersionRetriever versionRetriever;

    protected String APIKEY = "api_key";

    protected String NOTAVAILABLE = "<b>HOST NOT AVAILABLE</b>";
    protected String REFERTOABOVE = "<b>REFER TO TABLE ABOVE</b>";
    protected String EXCEPTION1 = "current thread was interrupted while waiting: ";
    protected String EXCEPTION2 = "computation threw an exception: ";

    public String getVersionValueBody(Future<String> future) {
        String versionValueBody = "";
        try {
            versionValueBody = future.get();
        } catch (ExecutionException e) {
            log.error(EXCEPTION2 + e.getMessage());
        } catch (InterruptedException e) {
            log.error(EXCEPTION1 + e.getMessage());
        }
        return versionValueBody;
    }
}

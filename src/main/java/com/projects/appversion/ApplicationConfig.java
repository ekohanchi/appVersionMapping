package com.projects.appversion;

import static com.projects.appversion.ApplicationConfig.CLASSPATH_PROPERTY_RESOURCE;
import static com.projects.appversion.ApplicationConfig.FILE_PROPERTY_RESOURCE;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource({ CLASSPATH_PROPERTY_RESOURCE, FILE_PROPERTY_RESOURCE })
public class ApplicationConfig {
    public static final String FILE_PROPERTY_RESOURCE = "file:${APISERVICES_HOME}/conf/application.properties";
    public static final String CLASSPATH_PROPERTY_RESOURCE = "classpath:application.properties";
    
    @Value("${proxy.host}")
    private String proxyHost;

    @Value("${proxy.port}")
    private int proxyPort;
    
    @Value("${proxy.ignore}")
    private boolean proxyIgnore;

    @Value("${read.timeout}")
    private int READ_TIME_OUT;

    @Value("${connection.timeout}")
    private int CONNECTION_TIME_OUT;
    
    @Value("${server.port}")
    private int serverPort;
    
    @Value("${security.encryptPassword}")
    private String encryptPassword;
    
    @Value("${security.encryptSalt}")
    private String encryptSalt;
    
    @Value("${server.hostname}")
    private String serverHostname;
    
    public int getAppPort() {
        return serverPort;
    }
    
    public String getEncryptPassword() {
        return encryptPassword;
    }
    
    public String getEncryptSalt() {
        return encryptSalt;
    }
    
    public String getServerHostName() {
    	return serverHostname;
    }

    @Bean
    public RestTemplate createRestTemplate() {
        RequestConfig requestConfig = null;
        if (proxyIgnore) {
            requestConfig = RequestConfig.custom().build();

        } else {
            HttpHost proxy = new HttpHost(proxyHost, proxyPort);
            requestConfig = RequestConfig.custom().setProxy(proxy).build();
        }
        HttpClientBuilder httpClientBuilder = HttpClients.custom().useSystemProperties();
        httpClientBuilder.setDefaultRequestConfig(requestConfig);
        HttpClient httpClient = httpClientBuilder.build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        requestFactory.setReadTimeout(READ_TIME_OUT);
        requestFactory.setConnectTimeout(CONNECTION_TIME_OUT);

        return new RestTemplate(requestFactory);
    }
    
    @Bean
    public TextEncryptor createTextEncryptor() {
        TextEncryptor textEncryptor = Encryptors.text(encryptPassword, encryptSalt);
        return textEncryptor;
    }
}

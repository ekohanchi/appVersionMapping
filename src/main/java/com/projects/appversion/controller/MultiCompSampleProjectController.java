package com.projects.appversion.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projects.appversion.controller.model.AppEnvVersion;

@Controller
public class MultiCompSampleProjectController extends BaseController {
    private String APIAPP = "Multi Comp Sample API Project";
    private final static Logger log = LoggerFactory.getLogger(MultiCompSampleProjectController.class.getName());

    @RequestMapping("/multicompsampleproject")

    public String index(Model model) {
        // This will take care of the DEV, QA, & UAT Envs
        model.addAttribute("applications", populateDevQaUAT());

        // This will take care of the STAGE & PROD Envs
        model.addAttribute("applicationsStageProd", populateStageProd());

        // This will take care of the DEV, QA, & UAT Envs with Mashery
        model.addAttribute("applicationsMashery", populateDevQaUATMashery());

        // This will take care of the STAGE & PROD Envs with Mashery
        model.addAttribute("applicationsStageProdMashery", populateStageProdMashery());

        return "multicompsampleproject";

    }

    private List<AppEnvVersion> populateDevQaUAT() {
    	LinkedHashMap<String, List<String>> lhm = new LinkedHashMap<String, List<String>>();
    	lhm.put("Component 1",
                Arrays.asList("http://localhost:8090/project/versiontest3",
                        "http://localhost:8090/project/versiontest2",
                        "http://localhost:8090/project/versiontest1"));
    	lhm.put("Component 2",
                Arrays.asList("http://localhost:8090/project/versiontest3",
                        "http://localhost:8090/project/versiontest2",
                        "http://localhost:8090/project/versiontest1"));
    	lhm.put("Component 3",
                Arrays.asList("http://localhost:8090/project/versiontest3",
                        "http://localhost:8090/project/versiontest2",
                        "http://localhost:8090/project/versiontest1"));
    	lhm.put("Component 4",
                Arrays.asList("http://localhost:8090/project/versiontest3",
                        "http://localhost:8090/project/versiontest2",
                        "http://localhost:8090/project/versiontest1"));
    	lhm.put("Component 5",
                Arrays.asList("http://localhost:8090/project/versiontest3",
                        "http://localhost:8090/project/versiontest2",
                        "http://localhost:8090/project/versiontest1"));
    	
    	List<AppEnvVersion> appEnvVersionList = new ArrayList<AppEnvVersion>();
    	LinkedHashMap<String, List<Future<String>>> lhm_future = new LinkedHashMap<String, List<Future<String>>>();
    	String devUrl, qaUrl, uatUrl;
    	for (Map.Entry<String, List<String>> entry : lhm.entrySet()) {
    		devUrl = entry.getValue().get(0);
    		qaUrl = entry.getValue().get(1);
    		uatUrl = entry.getValue().get(2);
    		log.info("Getting Future Origin info for component: " + entry.getKey());
    		lhm_future.put(entry.getKey(), getFuture(devUrl, qaUrl, uatUrl));
    				
    	}
    	
    	for (Map.Entry<String, List<Future<String>>> entry : lhm_future.entrySet()) {
    		appEnvVersionList.add(addComponent(entry.getKey(), entry.getValue()));
    	}
    	
    	
        return appEnvVersionList;
    }

    private List<AppEnvVersion> populateStageProd() {
    	LinkedHashMap<String, List<String>> lhm = new LinkedHashMap<String, List<String>>();
    	lhm.put("Component 1",
                Arrays.asList("http://localhost:8090/project/versiontest2",
                        "http://localhost:8090/project/versiontest1"));
    	lhm.put("Component 2",
                Arrays.asList("http://localhost:8090/project/versiontest2",
                        "http://localhost:8090/project/versiontest1"));
    	lhm.put("Component 3",
                Arrays.asList("http://localhost:8090/project/versiontest2",
                        "http://localhost:8090/project/versiontest1"));
    	lhm.put("Component 4",
                Arrays.asList("http://localhost:8090/project/versiontest2",
                        "http://localhost:8090/project/versiontest1"));
    	lhm.put("Component 5",
                Arrays.asList("http://localhost:8090/project/versiontest2",
                        "http://localhost:8090/project/versiontest1"));
    	
    	List<AppEnvVersion> appEnvVersionList = new ArrayList<AppEnvVersion>();
    	LinkedHashMap<String, List<Future<String>>> lhm_future = new LinkedHashMap<String, List<Future<String>>>();
    	String stageUrl, prodUrl;
    	for (Map.Entry<String, List<String>> entry : lhm.entrySet()) {
    		stageUrl = entry.getValue().get(0);
    		prodUrl = entry.getValue().get(1);
    		log.info("Getting Future Origin info for component: " + entry.getKey());
    		lhm_future.put(entry.getKey(), getFuture(stageUrl, prodUrl));
    				
    	}
    	
    	for (Map.Entry<String, List<Future<String>>> entry : lhm_future.entrySet()) {
    		appEnvVersionList.add(addComponent(entry.getKey(), entry.getValue()));
    	}
    	
    	
        return appEnvVersionList;
    }

    private List<AppEnvVersion> populateDevQaUATMashery() {
        Hashtable<String, String> ht = new Hashtable<String, String>();
        ht.put("dev", "");
        ht.put("qa", "");
        ht.put("uat", "");

        List<AppEnvVersion> appEnvVersionList = new ArrayList<AppEnvVersion>();
        AppEnvVersion appEnvVersion = new AppEnvVersion();

        appEnvVersion.setName(APIAPP);
        Future<String> dev = versionRetriever.getAsyncVersionValueBody(ht.get("dev"), "");
        Future<String> qa = versionRetriever.getAsyncVersionValueBody(ht.get("qa"), "");
        Future<String> uat = versionRetriever.getAsyncVersionValueBody(ht.get("uat"), "");

        appEnvVersion.setDevversion(getVersionValueBody(dev));
        appEnvVersion.setQaversion(getVersionValueBody(qa));
        appEnvVersion.setUatversion(getVersionValueBody(uat));
        appEnvVersionList.add(appEnvVersion);
        return appEnvVersionList;
    }

    private List<AppEnvVersion> populateStageProdMashery() {
        Hashtable<String, String> ht = new Hashtable<String, String>();
        ht.put("stage", "");
        ht.put("prod", "");

        List<AppEnvVersion> appEnvVersionList = new ArrayList<AppEnvVersion>();
        AppEnvVersion appEnvVersion = new AppEnvVersion();

        appEnvVersion.setName(APIAPP);
        Future<String> stage = versionRetriever.getAsyncVersionValueBody(ht.get("stage"), "");
        Future<String> prod = versionRetriever.getAsyncVersionValueBody(ht.get("prod"), "");

        appEnvVersion.setStageversion(getVersionValueBody(stage));
        appEnvVersion.setProdversion(getVersionValueBody(prod));

        appEnvVersionList.add(appEnvVersion);
        return appEnvVersionList;
    }
    
    private List<Future<String>> getFuture(String... urls) {
		List<Future<String>> futureList = new ArrayList<Future<String>>();
		if (urls.length == 3) {
			futureList.add(versionRetriever.getAsyncVersionValueBody(urls[0]));
			futureList.add(versionRetriever.getAsyncVersionValueBody(urls[1]));
			futureList.add(versionRetriever.getAsyncVersionValueBody(urls[2]));
		} else if (urls.length == 2) {
			futureList.add(versionRetriever.getAsyncVersionValueBody(urls[0]));
			futureList.add(versionRetriever.getAsyncVersionValueBody(urls[1]));
		}
		
		return futureList;
	}
    
    private AppEnvVersion addComponent(String name, List<Future<String>> list) {
    	AppEnvVersion appEnvVersion = new AppEnvVersion();
    	if (list.size() == 3) {
	    	appEnvVersion.setName(name);
	    	appEnvVersion.setDevversion(getVersionValueBody(list.get(0)));
	    	appEnvVersion.setQaversion(getVersionValueBody(list.get(1)));
	    	appEnvVersion.setUatversion(getVersionValueBody(list.get(2)));
    	} else if (list.size() == 2) {
    		appEnvVersion.setName(name);
        	appEnvVersion.setStageversion(getVersionValueBody(list.get(0)));
        	appEnvVersion.setProdversion(getVersionValueBody(list.get(1)));
    	}
    	
    	return appEnvVersion;
    }
}

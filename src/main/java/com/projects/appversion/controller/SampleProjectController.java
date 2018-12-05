package com.projects.appversion.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projects.appversion.controller.model.AppEnvVersion;

@Controller
public class SampleProjectController extends BaseController {
    private String APIAPP = "Sample API Project";

    @RequestMapping("/sampleproject")

    public String index(Model model) {
        // This will take care of the DEV, QA, & UAT Envs
        model.addAttribute("applications", populateDevQaUAT());

        // This will take care of the STAGE & PROD Envs
        model.addAttribute("applicationsStageProd", populateStageProd());

        // This will take care of the DEV, QA, & UAT Envs with Mashery
        model.addAttribute("applicationsMashery", populateDevQaUATMashery());

        // This will take care of the STAGE & PROD Envs with Mashery
        model.addAttribute("applicationsStageProdMashery", populateStageProdMashery());

        return "sampleproject";

    }

    private List<AppEnvVersion> populateDevQaUAT() {
        Hashtable<String, String> ht = new Hashtable<String, String>();
        ht.put("dev", "http://localhost:8090/project/versiontest3");
        ht.put("qa", "http://localhost:8090/project/versiontest2");
        ht.put("uat", "http://localhost:8090/project/versiontest1");
        List<AppEnvVersion> appEnvVersionList = new ArrayList<AppEnvVersion>();
        AppEnvVersion appEnvVersion = new AppEnvVersion();
        appEnvVersion.setName(APIAPP);
        Future<String> dev = versionRetriever.getAsyncVersionValueBody(ht.get("dev"));
        Future<String> qa = versionRetriever.getAsyncVersionValueBody(ht.get("qa"));
        Future<String> uat = versionRetriever.getAsyncVersionValueBody(ht.get("uat"));

        appEnvVersion.setDevversion(getVersionValueBody(dev));
        appEnvVersion.setQaversion(getVersionValueBody(qa));
        appEnvVersion.setUatversion(getVersionValueBody(uat));
        appEnvVersionList.add(appEnvVersion);
        return appEnvVersionList;
    }

    private List<AppEnvVersion> populateStageProd() {
        Hashtable<String, String> ht = new Hashtable<String, String>();
        ht.put("stage1", "http://localhost:8090/project/versiontest1");
        ht.put("stage2", "http://localhost:8090/project/versiontest1");
        ht.put("prod1", "http://localhost:8090/project/versiontest1");
        ht.put("prod2", "http://localhost:8090/project/versiontest1");
        List<AppEnvVersion> appEnvVersionList = new ArrayList<AppEnvVersion>();
        AppEnvVersion appEnvVersion = new AppEnvVersion();
        appEnvVersion.setName(APIAPP);
        Future<String> stage1 = versionRetriever.getAsyncVersionValueBody(ht.get("stage1"));
        Future<String> stage2 = versionRetriever.getAsyncVersionValueBody(ht.get("stage2"));
        Future<String> prod1 = versionRetriever.getAsyncVersionValueBody(ht.get("prod1"));
        Future<String> prod2 = versionRetriever.getAsyncVersionValueBody(ht.get("prod2"));

        appEnvVersion.setStage1version(getVersionValueBody(stage1));
        appEnvVersion.setStage2version(getVersionValueBody(stage2));
        appEnvVersion.setProd1version(getVersionValueBody(prod1));
        appEnvVersion.setProd2version(getVersionValueBody(prod2));
        appEnvVersionList.add(appEnvVersion);
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
}

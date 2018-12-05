package com.projects.appversion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projects.appversion.controller.model.Version;

@Controller
public class VersionTestController {

    @RequestMapping(method=RequestMethod.GET, value="/project/versiontest1", 
    headers="Accept=application/json")
    public @ResponseBody Version VersionTest1() {
        Version version = new Version();
        version.setVersion("1.0.0-SNAPSHOT");
        version.setDate("2018-12-03T11:02:48.068+0000");
        return version;
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/project/versiontest2", 
    headers="Accept=application/json")
    public @ResponseBody Version VersionTest2() {
        Version version = new Version();
        version.setVersion("1.2.0-SNAPSHOT");
        version.setDate("2018-12-04T11:02:48.068+0000");
        return version;
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/project/versiontest3", 
    headers="Accept=application/json")
    public @ResponseBody Version VersionTest3() {
        Version version = new Version();
        version.setVersion("1.3.0-SNAPSHOT");
        version.setDate("2018-12-05T11:02:48.068+0000");
        return version;
    }

}

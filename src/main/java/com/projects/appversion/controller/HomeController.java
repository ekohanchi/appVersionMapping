package com.projects.appversion.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projects.appversion.controller.model.Projects;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("projects", populateProjectList());

        return "index";
    }

    private List<Projects> populateProjectList() {
        List<Projects> projectsList = new ArrayList<Projects>();
        Projects project = new Projects();
        project.setName("Sample Project");
        project.setLink("/sampleproject");
        projectsList.add(project);
        
        project = new Projects();
        project.setName("Multi Comp Sample Project");
        project.setLink("/multicompsampleproject");
        projectsList.add(project);


        return projectsList;
    }

}

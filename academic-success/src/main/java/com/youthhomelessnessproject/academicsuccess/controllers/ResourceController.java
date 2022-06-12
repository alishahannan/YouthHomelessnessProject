package com.youthhomelessnessproject.academicsuccess.controllers;

import com.youthhomelessnessproject.academicsuccess.models.Employee;
import com.youthhomelessnessproject.academicsuccess.models.Resource;
import com.youthhomelessnessproject.academicsuccess.models.Student;
import com.youthhomelessnessproject.academicsuccess.services.ResourceService;
import com.youthhomelessnessproject.academicsuccess.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller

public class ResourceController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/resource/{id}")
    public ModelAndView showResourceDetails(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("/student-resource-details");
        Resource resource = resourceService.findResourceById(id);
        mav.addObject("student", ContextController.getStudent());
        mav.addObject("resource", resource);

        return mav;
    }

    @GetMapping("/resources")
    public ModelAndView showAllResources() {
        ModelAndView mav = new ModelAndView("student-resources-list-all");
        List<Resource> resources = resourceService.getAllResources();
        mav.addObject("student", ContextController.getStudent());
        mav.addObject("resources", resources);
        return mav;
    }


}

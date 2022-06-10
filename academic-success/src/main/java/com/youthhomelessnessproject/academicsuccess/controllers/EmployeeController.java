package com.youthhomelessnessproject.academicsuccess.controllers;

import com.youthhomelessnessproject.academicsuccess.dto.ResourcesDTO;
import com.youthhomelessnessproject.academicsuccess.models.*;
import com.youthhomelessnessproject.academicsuccess.repositories.AddressRepository;
import com.youthhomelessnessproject.academicsuccess.repositories.ResourceTagRepository;
import com.youthhomelessnessproject.academicsuccess.services.EmployeeService;
import com.youthhomelessnessproject.academicsuccess.services.ResourceService;
import com.youthhomelessnessproject.academicsuccess.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {

    // TODO figure out if ResourceTagRepo is necessary because
    //  we want to use the same handful of tags for every resource & question

    private EmployeeService employeeService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private AddressRepository addressRepository;

//    @Autowired
//    private ResourceTagRepository resourceTagRepository;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    @GetMapping("/employee/dashboard")
    public String showEmployeeDashboard(Model model) {
        List<Session> sessions = new ArrayList<>();
        for(Session session : sessionService.getAllSessions()) {
            if(session.getEnd_time() != null) {
                sessions.add(session);
            }
        }
        model.addAttribute("allSessions", sessions);
        model.addAttribute("employee", employeeService.getEmployeeById(ContextController.getEmployee().getId()));
        return "employee-dashboard";
    }

    // TODO Thymeleaf templates fore these endpoints!
    @GetMapping("/employee/resource")
    public String showAddResourceForm(Model model) {
        ResourcesDTO resourcesDto = new ResourcesDTO();
        model.addAttribute("resourcesDto", resourcesDto);
        model.addAttribute("employee",
                employeeService.getEmployeeById(ContextController.getEmployee().getId()));
        return "employee-add-resource";
    }

    @PostMapping("employee/resource/add")
    public String addResource(@ModelAttribute ResourcesDTO resourcesDto, Model model) {
        Resource resource = new Resource();
        List<ResourceTag> resourceTags = new ArrayList<>();
        resourceTags.addAll(resourcesDto.getTags());
        resource.setName(resourcesDto.getName());
        resource.setDescription(resourcesDto.getDescription());
        resource.setAddress(resourcesDto.getAddress());

        for(ResourceTag tag : resourceTags) {
            tag.setResource(resource);
        }

        resource.setTags(resourceTags);
        resourceService.saveResource(resource);

        return "redirect:/employee/resources/list";
    }

    @GetMapping("/employee/resource/{id}")
    public String showModifyResourceForm(@PathVariable Long id, Model model) {
        Resource existingResource = resourceService.findResourceById(id);
        ResourcesDTO resourcesDto = new ResourcesDTO();
        resourcesDto.setName(existingResource.getName());
        resourcesDto.setDescription(existingResource.getDescription());
        resourcesDto.setAddress(existingResource.getAddress());
        resourcesDto.setTags(existingResource.getTags());
        model.addAttribute("resourcesDto", resourcesDto);

        return "employee-resources-edit";
    }

    @PostMapping("/employee/resource/{id}")
    public String updateResourceDetails(@PathVariable Long id, @ModelAttribute ResourcesDTO resourcesDto, Model model) {
        Resource existingResource = resourceService.findResourceById(id);
//        List<ResourceTag> tags = existingResource.getTags();
        existingResource.setName(resourcesDto.getName());
        existingResource.setDescription(resourcesDto.getDescription());
        existingResource.setAddress(resourcesDto.getAddress());
        existingResource.setTags(resourcesDto.getTags());
        resourceService.saveResource(existingResource);

        return "redirect:/employee/resources/list";
    }

    @GetMapping("/employee/resources/list")
    public String showAllResourcesPage(Model model) {
        List<Resource> foodResources = resourceService.getAllResourcesByTag(new ResourceTag(ResourceTag.Tag.FOOD));
        List<Resource> housingResources = resourceService.getAllResourcesByTag(new ResourceTag(ResourceTag.Tag.HOUSING));
        List<Resource> dependentResources = resourceService.getAllResourcesByTag(new ResourceTag(ResourceTag.Tag.DEPENDENT));
        model.addAttribute("foodResources", foodResources);
        model.addAttribute("housingResources", housingResources);
        model.addAttribute("dependentResources", dependentResources);

        return "employee-resources-list";
    }

    @GetMapping("/employee/resource/delete/{id}")
    public String deleteResources(@PathVariable Long id, Model model) {
        Resource resource = resourceService.findResourceById(id);

        // Delete properties associated with resource and resource
        addressRepository.delete(resource.getAddress());

        // TODO resource tags should be static & reusable
        //  this way, we use the same tags for each resource/question/quiz
        //  They should not be deleted
//        List<ResourceTag> tags = question.getTags();
//        for(ResourceTag tag : tags) {
//            resourceTagRepository.delete(tag);
//        }
        resourceService.deleteResourceById(id);

        return "redirect:/employee/resources/list";
    }




    // ADMIN ACTIONS ON SURVEYADMIN ENTITY*****
    @GetMapping("/employee/edit/{id}")
    public String showEmployeeEditPage(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("survey-admin", employee);
        return "admin-survey-admin-edit";
    }

    @PostMapping("/employee/edit/{id}")
    public String updateEmployeeDetails(@PathVariable Long id, @ModelAttribute("admin") Employee employee, Model model) {
        Employee existingEmployee = employeeService.getEmployeeById(id);
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setUsername(employee.getUsername());
        existingEmployee.setPassword(employee.getPassword());
        employeeService.saveEmployee(existingEmployee);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/employee/delete/{id}")
    public String deleteSurveyAdmin(@PathVariable Long id, Model model) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/admin/dashboard";
    }

}

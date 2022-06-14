package com.youthhomelessnessproject.academicsuccess.controllers;

import com.youthhomelessnessproject.academicsuccess.dto.ResourcesDTO;
import com.youthhomelessnessproject.academicsuccess.models.*;
import com.youthhomelessnessproject.academicsuccess.repositories.AddressRepository;
import com.youthhomelessnessproject.academicsuccess.services.AddressService;
import com.youthhomelessnessproject.academicsuccess.services.EmployeeService;
import com.youthhomelessnessproject.academicsuccess.services.ResourceService;
import com.youthhomelessnessproject.academicsuccess.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @Autowired
    private AddressService addressService;

//    @Autowired
//    private ResourceTagRepository resourceTagRepository;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    @GetMapping("/employee/dashboard")
    public String showEmployeeDashboard(Model model) {
        List<Resource> resources = new ArrayList<>();
        resources.addAll(resourceService.getAllResources());

        model.addAttribute("allResources", resources);
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
        resource.setFoodResource(resourcesDto.getFoodResource());
        resource.setHousingResource(resourcesDto.getHousingResource());
        resource.setDependentResource(resourcesDto.getDependentResource());
        resource.setName(resourcesDto.getName());
        resource.setDescription(resourcesDto.getDescription());
        resource.setDegree(resourcesDto.getDegree());

        // Set resource address
        resource.setAddress(resourcesDto.getAddress());

        // Set address resource
        resource.getAddress().setResource(resource);

        resourceService.saveResource(resource);
        // TODO verify that following line did not break method!!
        addressRepository.save(resource.getAddress());

        return "redirect:/employee/resources/list";
    }

    @GetMapping("/employee/resource/{id}")
    public ModelAndView showResourceDetails(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("employee-resource-details");
        Resource resource = resourceService.findResourceById(id);
        mav.addObject("employee", ContextController.getEmployee());
        mav.addObject("resource", resource);

        return mav;
    }

    @GetMapping("/employee/resource/update/{id}")
    public ModelAndView showModifyResourceForm(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("employee-add-resource");
        Resource existingResource = resourceService.findResourceById(id);
        ResourcesDTO resourcesDto = new ResourcesDTO();
        resourcesDto.setName(existingResource.getName());
        resourcesDto.setDescription(existingResource.getDescription());
        resourcesDto.setAddress(existingResource.getAddress());
        resourcesDto.setFoodResource(existingResource.getFoodResource());
        resourcesDto.setHousingResource(existingResource.getHousingResource());
        resourcesDto.setDependentResource(existingResource.getDependentResource());
        mav.addObject("resourcesDto", resourcesDto);
        mav.addObject("employee",
                employeeService.getEmployeeById(ContextController.getEmployee().getId()));
        mav.addObject("resource", existingResource);

        return mav;
//        model.addAttribute("resourcesDto", resourcesDto);
//        return "employee-resources-edit";
    }

    @PostMapping("/employee/resource/update/{id}")
    public String updateResourceDetails(@PathVariable Long id, @ModelAttribute ResourcesDTO resourcesDto, Model model) {
        Resource existingResource = resourceService.findResourceById(id);
        Address existingAddress = addressService.getAddressById(existingResource.getAddress().getId());
        existingResource.setName(resourcesDto.getName());
        existingResource.setDescription(resourcesDto.getDescription());
        existingAddress.setStreet(resourcesDto.getAddress().getStreet());
        existingAddress.setCity(resourcesDto.getAddress().getCity());
        existingResource.setAddress(resourcesDto.getAddress());
        existingResource.setFoodResource(resourcesDto.getFoodResource());
        existingResource.setHousingResource(resourcesDto.getHousingResource());
        existingResource.setDependentResource(resourcesDto.getDependentResource());
        existingResource.setDegree(resourcesDto.getDegree());
        resourceService.saveResource(existingResource);
        existingAddress.setResource(existingResource);
        addressService.saveAddress(existingAddress);

        return "redirect:/employee/resources/list";
    }

    @GetMapping("/employee/resources/list")
    public ModelAndView showAllResourcesPage() {
        ModelAndView mav = new ModelAndView("employee-resources-list");
        List<Resource> resources = new ArrayList<>();
        resources.addAll(resourceService.getAllResources());

        mav.addObject("allResources", resources);
        mav.addObject("employee", employeeService.getEmployeeById(ContextController.getEmployee().getId()));

        return mav;
    }

    @GetMapping("/employee/resource/delete/{id}")
    public String deleteResources(@PathVariable Long id, Model model) {
        Resource resource = resourceService.findResourceById(id);

        // Delete properties associated with resource and resource
        addressRepository.delete(resource.getAddress());

        resourceService.deleteResourceById(id);

        return "redirect:/employee/resources/list";
    }




    // ADMIN ACTIONS ON SURVEY-ADMIN ENTITY*****
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

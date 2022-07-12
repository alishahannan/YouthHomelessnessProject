package com.youthhomelessnessproject.academicsuccess.controllers;

import com.youthhomelessnessproject.academicsuccess.dto.QuestionsDTO;
import com.youthhomelessnessproject.academicsuccess.dto.ResourcesDTO;
import com.youthhomelessnessproject.academicsuccess.models.*;
import com.youthhomelessnessproject.academicsuccess.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private SessionService sessionService;
    // Will need sessionService later for report building functionality

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private AddressService addressService;


    @GetMapping("/employee/dashboard")
    public String showEmployeeDashboard(Model model) {
        List<Resource> resources = resourceService.getAllResources();
        model.addAttribute("allResources", resources);
        model.addAttribute("employee", employeeService.getEmployeeById(ContextController.getEmployee().getId()));
        return "employee-dashboard";
    }

    @GetMapping("/employee/questions/new")
    public String showAddQuestionForm(Model model) {
        QuestionsDTO questionsDto = new QuestionsDTO();
        List<Option> options = new ArrayList<>();

        for(int i = 0; i < 5; i++) {
            Option option = new Option("", 0);
            options.add(option);
        }
        questionsDto.setOptions(options);
        model.addAttribute("questionsDto", questionsDto);
        model.addAttribute("employee",
                employeeService.getEmployeeById(ContextController.getEmployee().getId()));
        return "employee-add-question";
    }

    @PostMapping("/employee/questions/new")
    public String addQuestion(@ModelAttribute QuestionsDTO questionsDto) {
        setQuestionProps(questionsDto, -1);
        return "redirect:/employee/questions";
    }

    @GetMapping("/employee/questions")
    public String showAllQuestionsPage(Model model) {
        List<Question> questions = questionService.getAllQuestions();
        model.addAttribute("questions", questions);
        model.addAttribute("employee", employeeService.getEmployeeById(ContextController.getEmployee().getId()));
        return "employee-questions-list";
    }

    @GetMapping("/employee/questions/{id}/edit")
    public String showModifyQuestionForm(@PathVariable Long id, Model model) {
        Question existingQuestion = questionService.findQuestionById(id);
        QuestionsDTO questionsDto = new QuestionsDTO();

        // Set questions title and available options
        questionsDto.setTitle(existingQuestion.getTitle());
        questionsDto.setQuestionOptions(existingQuestion.getOptions());
        questionsDto.setFoodResource(existingQuestion.getFoodResource());
        questionsDto.setHousingResource(existingQuestion.getHousingResource());
        questionsDto.setDependentResource(existingQuestion.getDependentResource());
        questionsDto.setId(existingQuestion.getId());
        model.addAttribute("questionsDto", questionsDto);
        model.addAttribute("employee",
                employeeService.getEmployeeById(ContextController.getEmployee().getId()));
        return "employee-edit-question";
    }

    @PostMapping("/employee/questions/{id}/edit")
    public String updateQuestionDetails(@PathVariable Long id, @ModelAttribute QuestionsDTO questionsDto) {
        setQuestionProps(questionsDto, id);
        return "redirect:/employee/questions";
    }

    @GetMapping("/employee/questions/{id}/delete")
    public String deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestionById(id);
        return "redirect:/employee/questions";
    }

    public void setQuestionProps(QuestionsDTO questionsDto, long id) {
        Question question;
        List<Option> options = questionsDto.getOptions();

        if(id == -1) {
            // Creating a new question
            question = new Question();
            System.out.println("Printing option titles from setQuestionPropsExp method: \n");
            for(Option option : options) {
                option.setQuestion(question);
                System.out.println("Title: " + option.getOptionTitle());
                System.out.println("Value: " + option.getValue());
            }

        } else {
            // Editing an existing question
            question = questionService.findQuestionById(id);
            List<Option> prevOptions = optionService.getOptionsByQuestionId(question.getId());
            int count = 0;
            System.out.println("Printing options from setQuestionPropsExp Method: ");
            for(Option o : options) {
                o.setId(prevOptions.get(count++).getId());
                o.setQuestion(question);
                System.out.println("Option: ID = " + o.getId() + ": title = " + o.getOptionTitle() + ", value = " + o.getValue());
            }
        }
        question.setTitle(questionsDto.getTitle());
        question.setFoodResource(questionsDto.getFoodResource());
        question.setHousingResource(questionsDto.getHousingResource());
        question.setDependentResource(questionsDto.getDependentResource());
        question.setOptions(options);

        questionService.saveQuestion(question);

        for(Option option : options) {
            optionService.saveOption(option);
        }

    }

    @GetMapping("/employee/resources/new")
    public String showAddResourceForm(Model model) {
        ResourcesDTO resourcesDto = new ResourcesDTO();
        model.addAttribute("resourcesDto", resourcesDto);
        model.addAttribute("employee",
                employeeService.getEmployeeById(ContextController.getEmployee().getId()));
        return "employee-add-resource";
    }

    @PostMapping("/employee/resources/new")
    public String addResource(@ModelAttribute ResourcesDTO resourcesDto, Model model) {
        Resource resource = new Resource();
        resource.setFoodResource(resourcesDto.getFoodResource());
        resource.setHousingResource(resourcesDto.getHousingResource());
        resource.setDependentResource(resourcesDto.getDependentResource());
        resource.setName(resourcesDto.getName());
        resource.setDescription(resourcesDto.getDescription());
        resource.setDegree(resourcesDto.getDegree());
        resource.setAddress(resourcesDto.getAddress());
        resource.getAddress().setResource(resource);
        resourceService.saveResource(resource);
        return "redirect:/employee/resources";
    }

    @GetMapping("/employee/resources/{id}")
    public String showResourceDetails(@PathVariable Long id, Model model) {
        ModelAndView mav = new ModelAndView("employee-resource-details");
        Resource resource = resourceService.findResourceById(id);
        model.addAttribute("resource", resource);
        model.addAttribute("employee",
                employeeService.getEmployeeById(ContextController.getEmployee().getId()));
        return "employee-resource-details";
    }

    @GetMapping("/employee/resources/{id}/edit")
    public String showModifyResourceForm(@PathVariable Long id, Model model) {
        Resource existingResource = resourceService.findResourceById(id);
        ResourcesDTO resourcesDto = new ResourcesDTO();
        resourcesDto.setName(existingResource.getName());
        resourcesDto.setDescription(existingResource.getDescription());
        resourcesDto.setAddress(existingResource.getAddress());
        resourcesDto.setDegree(existingResource.getDegree());
        resourcesDto.setFoodResource(existingResource.getFoodResource());
        resourcesDto.setHousingResource(existingResource.getHousingResource());
        resourcesDto.setDependentResource(existingResource.getDependentResource());
        model.addAttribute("resourcesDto", resourcesDto);
        model.addAttribute("resource", existingResource);
        model.addAttribute("resourceId", existingResource.getId());
        model.addAttribute("employee",
                employeeService.getEmployeeById(ContextController.getEmployee().getId()));
        return "employee-edit-resource";
    }

    @PostMapping("/employee/resources/{id}/edit")
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
        return "redirect:/employee/resources";
    }

    @GetMapping("/employee/resources")
    public String showAllResourcesPage(Model model) {
        List<Resource> resources = resourceService.getAllResources();
        model.addAttribute("allResources", resources);
        model.addAttribute("employee",
                employeeService.getEmployeeById(ContextController.getEmployee().getId()));
        return "employee-resources-list";
    }

    @GetMapping("/employee/resources/{id}/delete")
    public String deleteResources(@PathVariable Long id) {
        resourceService.deleteResourceById(id);
        return "redirect:/employee/resources";
    }


    // ADMIN ACTIONS ON EMPLOYEE ENTITY*****
    @GetMapping("/employee/edit/{id}")
    public String showEmployeeEditPage(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "admin-employee-edit";

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

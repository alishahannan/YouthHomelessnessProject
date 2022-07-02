package com.youthhomelessnessproject.academicsuccess.controllers;

import com.youthhomelessnessproject.academicsuccess.dto.QuestionsDTO;
import com.youthhomelessnessproject.academicsuccess.dto.ResourcesDTO;
import com.youthhomelessnessproject.academicsuccess.models.*;
import com.youthhomelessnessproject.academicsuccess.repositories.AddressRepository;
import com.youthhomelessnessproject.academicsuccess.repositories.OptionRepository;
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
    private OptionRepository optionRepository;

    @Autowired
    private SessionService sessionService;
    // Will need sessionService later for report building functionality

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;


    @GetMapping("/employee/dashboard")
    public String showEmployeeDashboard(Model model) {
        List<Resource> resources = resourceService.getAllResources();
        model.addAttribute("allResources", resources);
        model.addAttribute("employee", employeeService.getEmployeeById(ContextController.getEmployee().getId()));
        return "employee-dashboard";
    }

    // TODO remember you changed employee/question to questions/add
    // Add Question Page (GET)
    @GetMapping("/employee/questions/new")
    public ModelAndView showAddQuestionForm() {
        ModelAndView mav = new ModelAndView("employee-add-question");
        QuestionsDTO questionsDto = new QuestionsDTO();

        mav.addObject("questionsDto", questionsDto);
        mav.addObject("employee", employeeService.getEmployeeById(ContextController.getEmployee().getId()));
        return mav;
    }

    // Add Question POST
    @PostMapping("/employee/questions/new")
    public String addQuestion(@ModelAttribute QuestionsDTO questionsDto) {
        Question question = new Question();
        List<Option> options = new ArrayList<>();
        String optionText = "";
        Double optionValue = 0.0;
        if(!questionsDto.getOption1().isEmpty()) {
            optionText = questionsDto.getOption1();
            optionValue = questionsDto.getOption1Value();
            Option option1 = new Option(optionText, optionValue);
            option1.setQuestion(question);
            options.add(option1);
            question.setOption1(optionText);
            question.setOption1Value(optionValue);
        }

        if(!questionsDto.getOption2().isEmpty()) {
            optionText = questionsDto.getOption2();
            optionValue = questionsDto.getOption2Value();
            Option option2 = new Option(optionText, optionValue);
            option2.setQuestion(question);
            options.add(option2);
            question.setOption2(optionText);
            question.setOption2Value(optionValue);
        }

        if(!questionsDto.getOption3().isEmpty()) {
            optionText = questionsDto.getOption3();
            optionValue = questionsDto.getOption3Value();
            Option option3 = new Option(optionText, optionValue);
            option3.setQuestion(question);
            options.add(option3);
            question.setOption3(optionText);
            question.setOption3Value(optionValue);
        }

        if(!questionsDto.getOption4().isEmpty()) {
            optionText = questionsDto.getOption4();
            optionValue = questionsDto.getOption4Value();
            Option option4 = new Option(optionText, optionValue);
            option4.setQuestion(question);
            options.add(option4);
            question.setOption4(optionText);
            question.setOption4Value(optionValue);
        }

        if(!questionsDto.getOption5().isEmpty()) {
            optionText = questionsDto.getOption5();
            optionValue = questionsDto.getOption5Value();
            Option option5 = new Option(optionText, optionValue);
            option5.setQuestion(question);
            options.add(option5);
            question.setOption5(optionText);
            question.setOption5Value(optionValue);
        }

        question.setTitle(questionsDto.getTitle());
        question.setFoodResource(questionsDto.getFoodResource());
        question.setHousingResource(questionsDto.getHousingResource());
        question.setDependentResource(questionsDto.getDependentResource());

        question.setOptions(options);
        questionService.saveQuestion(question);

        for(Option option: options) {
            optionRepository.save(option);
        }

        return "redirect:/employee/questions";
    }

    // Show All Questions Page
    @GetMapping("/employee/questions")
    public ModelAndView showAllQuestionsPage() {
        ModelAndView mav = new ModelAndView("employee-questions-list");

        List<Question> questions = questionService.getAllQuestions();

        mav.addObject("questions", questions);

        return mav;
    }

    // TODO: implement these
    // Show Edit Question Page (GET)
//    @GetMapping("/employee/questions/{id}/edit")
//    public ModelAndView showModifyQuestionForm(@PathVariable Long id, Model model) {
//        ModelAndView mav = new ModelAndView("employee-edit-question");
//        Question existingQuestion = questionService.findQuestionById(id);
//        QuestionsDTO questionsDto = new QuestionsDTO();
//        questionsDto.setTitle(existingQuestion.getTitle());
//        // TODO check if this works!
//        //        List<Option> options = existingQuestion.getOptions();
//        questionsDto.setQuestionOptions(existingQuestion.getOptions());
//        questionsDto.setFoodResource(existingQuestion.getFoodResource());
//        questionsDto.setHousingResource(existingQuestion.getHousingResource());
//        questionsDto.setDependentResource(existingQuestion.getDependentResource());
//        questionsDto.setId(existingQuestion.getId());
//        mav.addObject("questionsDto", questionsDto);
//        return mav;
//    }
//
//
//    // Edit Question Page (POST)
//    @PostMapping("/employee/questions/{id}/edit")
//    public String updateQuestionDetails(@PathVariable Long id, @ModelAttribute QuestionsDTO questionsDto, Model model) {
//        // Retrieve question from DB and collect options and resource tags
//        Question existingQuestion = questionService.findQuestionById(id);
//        List<Option> existingOptions = existingQuestion.getOptions();
//        existingQuestion.setTitle(questionsDto.getTitle());
//        existingQuestion.setFoodResource(questionsDto.getFoodResource());
//        existingQuestion.setHousingResource(questionsDto.getHousingResource());
//        existingQuestion.setDependentResource(questionsDto.getDependentResource());
//
//        // TODO this won't work when trying to add MORE options..
//        // Use same logic as add new question, but using question ID
//        for(int i = 0; i < existingOptions.size(); i++) {
//            existingOptions.get(i).setOptionTitle(questionsDto.getQuestionOptionByIndex(i).getOptionTitle());
//            existingOptions.get(i).setValue(questionsDto.getQuestionOptionByIndex(i).getValue());
//        }
//        existingQuestion.setOptions(existingOptions);
//        questionService.saveQuestion(existingQuestion);
//        return "redirect:/employee/questions";
//    }

    // Delete question
    @GetMapping("/employee/questions/{id}/delete")
    public String deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestionById(id);
        return "redirect:/employee/questions";
    }


    // Add Resources Page (GET)
    // TODO Thymeleaf templates fore these endpoints!
    @GetMapping("/employee/resources/new")
    public String showAddResourceForm(Model model) {
        ResourcesDTO resourcesDto = new ResourcesDTO();
        model.addAttribute("resourcesDto", resourcesDto);
        model.addAttribute("employee",
                employeeService.getEmployeeById(ContextController.getEmployee().getId()));
        return "employee-add-resource";
    }
    // Add Resources POST
    @PostMapping("/employee/resources/new")
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
        addressRepository.save(resource.getAddress());

        return "redirect:/employee/resources";
    }

    @GetMapping("/employee/resources/{id}")
    public ModelAndView showResourceDetails(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("employee-resource-details");
        Resource resource = resourceService.findResourceById(id);
        mav.addObject("employee", ContextController.getEmployee());
        mav.addObject("resource", resource);
        return mav;
    }

    @GetMapping("/employee/resources/{id}/edit")
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
    public ModelAndView showAllResourcesPage() {
        ModelAndView mav = new ModelAndView("employee-resources-list");
        List<Resource> resources = resourceService.getAllResources();
//        resources.addAll(resourceService.getAllResources());
        mav.addObject("allResources", resources);
        mav.addObject("employee", employeeService.getEmployeeById(ContextController.getEmployee().getId()));
        return mav;
    }

    @GetMapping("/employee/resources/{id}/delete")
    public String deleteResources(@PathVariable Long id) {
        Resource resource = resourceService.findResourceById(id);

        // Delete properties associated with resource and resource
        addressRepository.delete(resource.getAddress());

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

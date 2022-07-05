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
    public ModelAndView showAddQuestionForm() {
        ModelAndView mav = new ModelAndView("employee-add-question");
        QuestionsDTO questionsDto = new QuestionsDTO();
        mav.addObject("questionsDto", questionsDto);
        mav.addObject("employee", employeeService.getEmployeeById(ContextController.getEmployee().getId()));
        return mav;
    }

    @PostMapping("/employee/questions/new")
    public String addQuestion(@ModelAttribute QuestionsDTO questionsDto) {
        setQuestionProps(questionsDto, -1);
        return "redirect:/employee/questions";
    }

    @GetMapping("/employee/questions")
    public ModelAndView showAllQuestionsPage() {
        ModelAndView mav = new ModelAndView("employee-questions-list");
        List<Question> questions = questionService.getAllQuestions();
        mav.addObject("questions", questions);
        return mav;
    }

    @GetMapping("/employee/questions/{id}/edit")
    public ModelAndView showModifyQuestionForm(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("employee-edit-question");
        Question existingQuestion = questionService.findQuestionById(id);
        QuestionsDTO questionsDto = new QuestionsDTO();

        // Set questions title and available options
        questionsDto.setTitle(existingQuestion.getTitle());

        if(existingQuestion.getOption1() != null) {
            questionsDto.setOption1(existingQuestion.getOption1());
            questionsDto.setOption1Value(existingQuestion.getOption1Value());
        }

        if(existingQuestion.getOption2() != null) {
            questionsDto.setOption2(existingQuestion.getOption2());
            questionsDto.setOption2Value(existingQuestion.getOption2Value());
        }

        if(existingQuestion.getOption3() != null) {
            questionsDto.setOption3(existingQuestion.getOption3());
            questionsDto.setOption3Value(existingQuestion.getOption3Value());
        }

        if(existingQuestion.getOption4() != null) {
            questionsDto.setOption4(existingQuestion.getOption4());
            questionsDto.setOption4Value(existingQuestion.getOption4Value());
        }

        if(existingQuestion.getOption5() != null) {
            questionsDto.setOption5(existingQuestion.getOption5());
            questionsDto.setOption5Value(existingQuestion.getOption5Value());
        }

        questionsDto.setQuestionOptions(existingQuestion.getOptions());
        questionsDto.setFoodResource(existingQuestion.getFoodResource());
        questionsDto.setHousingResource(existingQuestion.getHousingResource());
        questionsDto.setDependentResource(existingQuestion.getDependentResource());
        questionsDto.setId(existingQuestion.getId());
        mav.addObject("questionsDto", questionsDto);
        mav.addObject("employee", employeeService.getEmployeeById(ContextController.getEmployee().getId()));
        return mav;
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

    /* This method is used to set properties of new and existing questions
     * If id == -1, we are creating a new question. Otherwise, existing
     * question id is passed  */
    public void setQuestionProps(QuestionsDTO questionsDto, long id) {
        Question question;
        List<Option> optionsList = new ArrayList<>();
        Option option1;
        Option option2;
        Option option3;
        Option option4;
        Option option5;

        if(id == -1) {
            question = new Question();
        } else {
            question = questionService.findQuestionById(id);
            optionsList = optionService.getOptionsByQuestionId(question.getId());
        }

        List<Option> options = new ArrayList<>();
        String optionText = "";
        Double optionValue = 0.0;

        if(!questionsDto.getOption1().isEmpty()) {
            optionText = questionsDto.getOption1();
            optionValue = questionsDto.getOption1Value();

            if(optionsList.size() >= 1) {
                option1 = optionsList.get(0);
                option1.setOptionTitle(optionText);
                option1.setValue(optionValue);
            } else {
                option1 = new Option(optionText, optionValue);
            }
            option1.setQuestion(question);
            options.add(option1);
            question.setOption1(optionText);
            question.setOption1Value(optionValue);
        }
        if(!questionsDto.getOption2().isEmpty()) {
            optionText = questionsDto.getOption2();
            optionValue = questionsDto.getOption2Value();

            if(optionsList.size() >= 2) {
                option2 = optionsList.get(1);
                option2.setOptionTitle(optionText);
                option2.setValue(optionValue);
            } else {
                option2 = new Option(optionText, optionValue);
            }
            option2.setQuestion(question);
            options.add(option2);
            question.setOption2(optionText);
            question.setOption2Value(optionValue);
        }

        if(!questionsDto.getOption3().isEmpty()) {
            optionText = questionsDto.getOption3();
            optionValue = questionsDto.getOption3Value();

            if(optionsList.size() >= 3) {
                option3 = optionsList.get(2);
                option3.setOptionTitle(optionText);
                option3.setValue(optionValue);
            } else {
                option3 = new Option(optionText, optionValue);
            }
            option3.setQuestion(question);
            options.add(option3);
            question.setOption3(optionText);
            question.setOption3Value(optionValue);
        }
        if(!questionsDto.getOption4().isEmpty()) {
            optionText = questionsDto.getOption4();
            optionValue = questionsDto.getOption4Value();

            if(optionsList.size() >= 4) {
                option4 = optionsList.get(3);
                option4.setOptionTitle(optionText);
                option4.setValue(optionValue);
            } else {
                option4 = new Option(optionText, optionValue);
            }
            option4.setQuestion(question);
            options.add(option4);
            question.setOption4(optionText);
            question.setOption4Value(optionValue);
        }
        if(!questionsDto.getOption5().isEmpty()) {
            optionText = questionsDto.getOption5();
            optionValue = questionsDto.getOption5Value();

            if(optionsList.size() >= 5) {
                option5 = optionsList.get(4);
                option5.setOptionTitle(optionText);
                option5.setValue(optionValue);
            } else {
                option5 = new Option(optionText, optionValue);
            }
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
    public ModelAndView showResourceDetails(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("employee-resource-details");
        Resource resource = resourceService.findResourceById(id);
        mav.addObject("employee", ContextController.getEmployee());
        mav.addObject("resource", resource);
        return mav;
    }

    @GetMapping("/employee/resources/{id}/edit")
    public ModelAndView showModifyResourceForm(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("employee-edit-resource");
        Resource existingResource = resourceService.findResourceById(id);
        ResourcesDTO resourcesDto = new ResourcesDTO();
        resourcesDto.setName(existingResource.getName());
        resourcesDto.setDescription(existingResource.getDescription());
        resourcesDto.setAddress(existingResource.getAddress());
        resourcesDto.setDegree(existingResource.getDegree());
        resourcesDto.setFoodResource(existingResource.getFoodResource());
        resourcesDto.setHousingResource(existingResource.getHousingResource());
        resourcesDto.setDependentResource(existingResource.getDependentResource());
        mav.addObject("resourcesDto", resourcesDto);
        mav.addObject("employee",
                employeeService.getEmployeeById(ContextController.getEmployee().getId()));
        mav.addObject("resource", existingResource);
        mav.addObject("resourceId", existingResource.getId());
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
        mav.addObject("allResources", resources);
        mav.addObject("employee", employeeService.getEmployeeById(ContextController.getEmployee().getId()));
        return mav;
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

package com.youthhomelessnessproject.academicsuccess.controllers;

import com.youthhomelessnessproject.academicsuccess.dto.QuestionsDTO;
import com.youthhomelessnessproject.academicsuccess.models.*;
import com.youthhomelessnessproject.academicsuccess.repositories.OptionRepository;
import com.youthhomelessnessproject.academicsuccess.services.QuestionService;
import com.youthhomelessnessproject.academicsuccess.services.SessionService;
import com.youthhomelessnessproject.academicsuccess.services.StudentService;
import com.youthhomelessnessproject.academicsuccess.services.SurveyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SurveyAdminController {

    private SurveyAdminService surveyAdminService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private OptionRepository optionRepository;

    public SurveyAdminController(SurveyAdminService surveyAdminService) {
        super();
        this.surveyAdminService = surveyAdminService;
    }

    @GetMapping("/survey-admin/dashboard")
    public String showSurveyAdminDashboard(Model model) {
//        List<Session> sessions = new ArrayList<>();
        List<Question> questions = questionService.getAllQuestions();
        List<List<Option>> options = new ArrayList<>();
        for(int i = 0; i < questions.size(); i++) {
            options.add(i, questions.get(i).getOptions());
        }

        model.addAttribute("allQuestions", questions);
        model.addAttribute("allOptions", options);

        model.addAttribute("surveyAdmin", surveyAdminService.getSurveyAdminById(ContextController.getSurveyAdmin().getId()));
        return "survey-admin-dashboard";
    }

    @GetMapping("/survey-admin/question")
    public ModelAndView showAddQuestionForm() {
        ModelAndView mav = new ModelAndView("survey-admin-add-question");
        QuestionsDTO questionsDto = new QuestionsDTO();

        mav.addObject("questionsDto", questionsDto);

        mav.addObject("surveyAdmin", surveyAdminService.getSurveyAdminById(ContextController.getSurveyAdmin().getId()));
        return mav;
    }

    // TODO check correctness of this method
    @PostMapping("/survey-admin/question/add")
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

        // Not sure if this will work at present
        question.setOptions(options);
        questionService.saveQuestion(question);

        for(Option option: options) {
            optionRepository.save(option);
        }

        return "redirect:/survey-admin/questions/list";
    }

    @GetMapping("/survey-admin/question/{id}")
    public ModelAndView showModifyQuestionForm(@PathVariable Long id, Model model) {
        ModelAndView mav = new ModelAndView("survey-admin-questions-edit");
        Question existingQuestion = questionService.findQuestionById(id);
        QuestionsDTO questionsDto = new QuestionsDTO();
        questionsDto.setTitle(existingQuestion.getTitle());
        // TODO check if this works!
        //        List<Option> options = existingQuestion.getOptions();
        questionsDto.setQuestionOptions(existingQuestion.getOptions());
        questionsDto.setFoodResource(existingQuestion.getFoodResource());
        questionsDto.setHousingResource(existingQuestion.getHousingResource());
        questionsDto.setDependentResource(existingQuestion.getDependentResource());
        questionsDto.setId(existingQuestion.getId());
        mav.addObject("questionsDto", questionsDto);
        return mav;
    }

    // TODO FIX this method!!!
    @PostMapping("/survey-admin/question/{id}")
    public String updateQuestionDetails(@PathVariable Long id, @ModelAttribute QuestionsDTO questionsDto, Model model) {
        // Retrieve question from DB and collect options and resource tags
        Question existingQuestion = questionService.findQuestionById(id);
        List<Option> existingOptions = existingQuestion.getOptions();
        existingQuestion.setTitle(questionsDto.getTitle());
        existingQuestion.setFoodResource(questionsDto.getFoodResource());
        existingQuestion.setHousingResource(questionsDto.getHousingResource());
        existingQuestion.setDependentResource(questionsDto.getDependentResource());

        // TODO this won't work when trying to add MORE options..
        //  Same for tags. Maybe enter optionTitle and values as CSV?
        //  Figure this out!!!
        for(int i = 0; i < existingOptions.size(); i++) {
            existingOptions.get(i).setOptionTitle(questionsDto.getQuestionOptionByIndex(i).getOptionTitle());
            existingOptions.get(i).setValue(questionsDto.getQuestionOptionByIndex(i).getValue());
        }
        existingQuestion.setOptions(existingOptions);
        questionService.saveQuestion(existingQuestion);
        return "redirect:/survey-admin/questions/list";
    }

    @GetMapping("/survey-admin/questions/list")
    public ModelAndView showAllQuestionsPage() {

        ModelAndView mav = new ModelAndView("survey-admin-questions-list");

        List<Question> questions = questionService.getAllQuestions();

        List<List<String>> foodQuestionOptions = new ArrayList<>();

        mav.addObject("questions", questions);

        return mav;
    }

    @GetMapping("/survey-admin/question/delete/{id}")
    public String deleteQuestion(@PathVariable Long id, Model model) {
        Question question = questionService.findQuestionById(id);

        // TODO verify if this is necessary due to OnDeleteAction in Options Entity
//        List<Option> options = question.getOptions();
//        for(Option option : options) {
//            optionRepository.delete(option);
//        }

        questionService.deleteQuestionById(id);
        return "redirect:/survey-admin/questions/list";
    }

    // ADMIN ACTIONS ON SURVEYADMIN ENTITY*****
    @GetMapping("/survey-admin/edit/{id}")
    public String showSurveyAdminEditPage(@PathVariable Long id, Model model) {
        SurveyAdmin surveyAdmin = surveyAdminService.getSurveyAdminById(id);
        model.addAttribute("surveyAdmin", surveyAdmin);
        return "admin-survey-admin-edit";
    }

    @PostMapping("/survey-admin/edit/{id}")
    public String updateAdminDetails(@PathVariable Long id, @ModelAttribute("admin") SurveyAdmin surveyAdmin, Model model) {
        SurveyAdmin existingSurveyAdmin = surveyAdminService.getSurveyAdminById(id);
        existingSurveyAdmin.setFirstName(surveyAdmin.getFirstName());
        existingSurveyAdmin.setLastName(surveyAdmin.getLastName());
        existingSurveyAdmin.setUsername(surveyAdmin.getUsername());
        existingSurveyAdmin.setPassword(surveyAdmin.getPassword());
        surveyAdminService.saveSurveyAdmin(existingSurveyAdmin);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/survey-admin/delete/{id}")
    public String deleteSurveyAdmin(@PathVariable Long id, Model model) {
        surveyAdminService.deleteSurveyAdminById(id);
        return "redirect:/admin/dashboard";
    }

}

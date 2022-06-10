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
        List<Session> sessions = new ArrayList<>();
        for (Session session : sessionService.getAllSessions()) {
            if (session.getEnd_time() != null) {
                sessions.add(session);
            }
        }
        model.addAttribute("allSessions", sessions);
        model.addAttribute("survey-admin", surveyAdminService.getSurveyAdminById(ContextController.getSurveyAdmin().getId()));
        return "survey-admin-dashboard";
    }

    @GetMapping("/survey-admin/question")
    public String showAddQuestionForm(Model model) {
        QuestionsDTO questionsDto = new QuestionsDTO();
        model.addAttribute("questionsDTO", questionsDto);
        model.addAttribute("survey-admin", surveyAdminService.getSurveyAdminById(ContextController.getSurveyAdmin().getId()));
        return "survey-admin-add-question";
    }

    // TODO check correctness of this method
    @PostMapping("/survey-admin/question/add")
    public String addQuestion(@ModelAttribute QuestionsDTO questionsDto, Model model) {
        Question question = new Question();
        List<Option> options = new ArrayList<>();
        options.addAll(questionsDto.getQuestionOptions());
        question.setTitle(questionsDto.getQuestionTitle());
        question.setTags(questionsDto.getQuestionResourceTags());

        for(Option option: options) {
           option.setQuestion(question);
        }
        question.setOptions(options);
        questionService.saveQuestion(question);
        return "redirect:/survey-admin/questions/list";
    }

    @GetMapping("/survey-admin/question/{id}")
    public String showModifyQuestionForm(@PathVariable Long id, Model model) {
        Question existingQuestion = questionService.findQuestionById(id);
        QuestionsDTO questionsDto = new QuestionsDTO();
        questionsDto.setQuestionTitle(existingQuestion.getTitle());
        // TODO check if this works!
        //        List<Option> options = existingQuestion.getOptions();
        questionsDto.setQuestionOptions(existingQuestion.getOptions());
        questionsDto.setQuestionResourceTags(existingQuestion.getTags());
        questionsDto.setQuestionId(existingQuestion.getId());
        model.addAttribute("questionsDto", questionsDto);
        return "survey-admin-questions-edit";
    }

    // TODO FIX this method!!!
    @PostMapping("/survey-admin/question/{id}")
    public String updateQuestionDetails(@PathVariable Long id, @ModelAttribute QuestionsDTO questionsDto, Model model) {
        // Retrieve question from DB and collect options and resource tags
        Question existingQuestion = questionService.findQuestionById(id);
        List<Option> existingOptions = existingQuestion.getOptions();
        existingQuestion.setTitle(questionsDto.getQuestionTitle());
        existingQuestion.setTags(questionsDto.getQuestionResourceTags());

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
    public String showAllQuestionsPage(Model model) {
        List<Question> foodQuestions = questionService.getAllQuestionsByTags(new ResourceTag(ResourceTag.Tag.FOOD));
        List<Question> housingQuestions = questionService.getAllQuestionsByTags(new ResourceTag(ResourceTag.Tag.HOUSING));
        List<Question> dependentQuestions = questionService.getAllQuestionsByTags(new ResourceTag(ResourceTag.Tag.DEPENDENT));
        model.addAttribute("foodQuestions", foodQuestions);
        model.addAttribute("housingQuestions", housingQuestions);
        model.addAttribute("dependentQuestions", dependentQuestions);

        return "survey-admin-questions-list";
    }

    @GetMapping("/survey-admin/question/delete/{id}")
    public String deleteQuestion(@PathVariable Long id, Model model) {
        Question question = questionService.findQuestionById(id);
        List<Option> options = question.getOptions();
        for(Option option : options) {
            optionRepository.delete(option);
        }
        // TODO resource tags should be static & reusable
        //  this way, we use the same tags for each resource/question/quiz
        //  They should not be deleted
//        List<ResourceTag> tags = question.getTags();
//        for(ResourceTag tag : tags) {
//            resourceTagRepository.delete(tag);
//        }
        questionService.deleteQuestionById(id);
        return "redirect:/survey-admin/questions/list";
    }

    // ADMIN ACTIONS ON SURVEYADMIN ENTITY*****
    @GetMapping("/survey-admin/edit/{id}")
    public String showSurveyAdminEditPage(@PathVariable Long id, Model model) {
        SurveyAdmin surveyAdmin = surveyAdminService.getSurveyAdminById(id);
        model.addAttribute("survey-admin", surveyAdmin);
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

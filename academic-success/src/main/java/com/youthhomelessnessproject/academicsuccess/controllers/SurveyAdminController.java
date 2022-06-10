package com.youthhomelessnessproject.academicsuccess.controllers;

import com.youthhomelessnessproject.academicsuccess.dto.QuestionsDTO;
import com.youthhomelessnessproject.academicsuccess.models.*;
import com.youthhomelessnessproject.academicsuccess.repositories.OptionRepository;
import com.youthhomelessnessproject.academicsuccess.services.QuestionService;
import com.youthhomelessnessproject.academicsuccess.services.SessionService;
import com.youthhomelessnessproject.academicsuccess.services.StudentService;
import com.youthhomelessnessproject.academicsuccess.services.SurveyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SurveyAdminController {

    // Runtime injection of SurveyAdminService, StudentService, StudentService,
    // SessionService, QuestionService, and OptionRepository dependencies
    private final SurveyAdminService surveyAdminService;
    private final StudentService studentService;
    private final SessionService sessionService;
    private final QuestionService questionService;
    private final OptionRepository optionRepository;

    @Autowired
    public SurveyAdminController(SurveyAdminService surveyAdminService, StudentService studentService,
                                 SessionService sessionService, QuestionService questionService,
                                 OptionRepository optionRepository) {
        super();
        this.surveyAdminService = surveyAdminService;
        this.studentService = studentService;
        this.sessionService = sessionService;
        this.questionService = questionService;
        this.optionRepository = optionRepository;
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
        QuestionsDTO questionsDTO = new QuestionsDTO();
        model.addAttribute("questionsDTO", questionsDTO);
        model.addAttribute("survey-admin", surveyAdminService.getSurveyAdminById(ContextController.getSurveyAdmin().getId()));
        return "survey-admin-add-question";
    }

    // TODO check correctness of this method
    @PostMapping("/survey-admin/question/add")
    public String addQuestion(@ModelAttribute QuestionsDTO questionsDTO, Model model) {
        Question question = new Question();
        List<Option> options = new ArrayList<>();
        options.addAll(questionsDTO.getQuestionOptions());
        question.setTitle(questionsDTO.getQuestionTitle());
        question.setTags(questionsDTO.getQuestionResourceTags());

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
        QuestionsDTO questionsDTO = new QuestionsDTO();
        questionsDTO.setQuestionTitle(existingQuestion.getTitle());
        // TODO check if this works!
        //        List<Option> options = existingQuestion.getOptions();
        questionsDTO.setQuestionOptions(existingQuestion.getOptions());
        questionsDTO.setQuestionResourceTags(existingQuestion.getTags());
        questionsDTO.setQuestionId(existingQuestion.getId());
        model.addAttribute("questionsDTO", questionsDTO);
        return "survey-admin-questions-edit";
    }

    // TODO FIX this method!!!
    @PostMapping("/survey-admin/question/{id}")
    public String updateQuestionDetails(@PathVariable Long id, @ModelAttribute QuestionsDTO questionsDTO, Model model) {
        // Retrieve question from DB and collect options and resource tags
        Question existingQuestion = questionService.findQuestionById(id);
        List<Option> existingOptions = existingQuestion.getOptions();
        existingQuestion.setTitle(questionsDTO.getQuestionTitle());
        existingQuestion.setTags(questionsDTO.getQuestionResourceTags());

        // TODO this won't work when trying to add MORE options..
        //  Same for tags. Maybe enter optionTitle and values as CSV?
        //  Figure this out!!!
        for(int i = 0; i < existingOptions.size(); i++) {
            existingOptions.get(i).setOptionTitle(questionsDTO.getQuestionOptionByIndex(i).getOptionTitle());
            existingOptions.get(i).setValue(questionsDTO.getQuestionOptionByIndex(i).getValue());
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

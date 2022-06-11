package com.youthhomelessnessproject.academicsuccess.controllers;

// TODO Implement this!!! This is where most of the business logic lies!

import com.youthhomelessnessproject.academicsuccess.dto.AnswersDTO;
import com.youthhomelessnessproject.academicsuccess.models.*;
import com.youthhomelessnessproject.academicsuccess.services.QuestionService;
import com.youthhomelessnessproject.academicsuccess.services.SessionService;
import com.youthhomelessnessproject.academicsuccess.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class SurveyController {

    // Runtime injection of QuestionService and SessionService dependencies
    private QuestionService questionService;

    @Autowired
    private SessionService sessionService;

    public SurveyController(QuestionService questionService) {
        super();
        this.questionService = questionService;
    }

    // TODO MAKE THIS HAPPEN!!!
    @GetMapping("/survey")
    public String showSurvey(Model model) {
        Session session = new Session();
        session.setStart_time(new Timestamp(System.currentTimeMillis()));
        List<Question> questions = questionService.getAllQuestions();
        Survey survey = new Survey(questions, session);
        session.setSurvey(survey);

        // TODO Find a better way to get/set scores.
        //  This may not even be necessary because session init with scores = 0
        session.setFoodScore(0.0);
        session.setHousingScore(0.0);
        session.setDependentScore(0.0);
        ContextController.getStudent().setSession(session);
        session.setStudentId(ContextController.getStudent().getId());
        session.setStudentName(ContextController.getStudent().getUsername());
        ContextController.setSession(session);
        sessionService.saveSession(session);
        ContextController.questions = questions;
        model.addAttribute("questions", questions);
        AnswersDTO answersDto = new AnswersDTO();
        model.addAttribute("answersDto", answersDto);
        model.addAttribute("student", ContextController.getStudent());

        return "survey";
    }

    @PostMapping("/survey/submit")
    public String validateSurvey(@ModelAttribute AnswersDTO answersDto, Model model) {

        double foodScore = 0;
        double housingScore = 0;
        double dependentScore = 0;

        List<Question> questions = ContextController.questions;
        Option[] submittedAnswers = answersDto.getAnswers();

        Session existingSession = sessionService.findSessionById(ContextController
                .getSession().getId());

        existingSession.setEnd_time(new Timestamp(System.currentTimeMillis()));

        // Calculate food, housing and dependent scores from survey responses
        for(int i = 0; i< questions.size(); i++) {

            if(questions.get(i).getFoodResource()) {

                double responseValue = submittedAnswers[i].getValue();

                foodScore += responseValue;

            } else if (questions.get(i).getHousingResource()) {

                double responseValue = submittedAnswers[i].getValue();

                housingScore += responseValue;

            } else if (questions.get(i).getDependentResource()) {

                double responseValue = submittedAnswers[i].getValue();

                dependentScore += responseValue;

            }

        }
        existingSession.setFoodScore(foodScore);
        System.out.println("Food score: " + foodScore);

        existingSession.setHousingScore(housingScore);
        System.out.println("Housing score: " + housingScore);

        existingSession.setDependentScore(dependentScore);
        System.out.println("Dependent score: " + dependentScore);

        model.addAttribute("food-score", foodScore);
        model.addAttribute("housing-score", housingScore);
        model.addAttribute("dependent-score", dependentScore);

//        model.addAttribute("total", "Out of " + questions.size());
        model.addAttribute("student", ContextController.getStudent());

        return "survey-results";

    }

}

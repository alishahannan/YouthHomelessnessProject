package com.youthhomelessnessproject.academicsuccess.controllers;

// TODO Implement this!!! This is where most of the business logic lies!

import com.youthhomelessnessproject.academicsuccess.dto.AnswersDTO;
import com.youthhomelessnessproject.academicsuccess.models.*;
import com.youthhomelessnessproject.academicsuccess.services.QuestionService;
import com.youthhomelessnessproject.academicsuccess.services.ResourceService;
import com.youthhomelessnessproject.academicsuccess.services.SessionService;
import com.youthhomelessnessproject.academicsuccess.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class SurveyController {

    // Runtime injection of QuestionService and SessionService dependencies
    private QuestionService questionService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ResourceService resourceService;
    

    public SurveyController(QuestionService questionService) {
        super();
        this.questionService = questionService;
    }

    // TODO MAKE THIS HAPPEN!!!
    @GetMapping("/survey")
    @ResponseBody
    public ModelAndView showSurvey(@RequestParam String zipcode) {
        AnswersDTO answersDto = new AnswersDTO();

       
        ModelAndView mav = new ModelAndView("survey");

        List<Question> questions = questionService.getAllQuestions();

        Session session = new Session();
        session.setStart_time(new Timestamp(System.currentTimeMillis()));

        Survey survey = new Survey(questions, session);
        session.setSurvey(survey);

        // TODO Find a better way to get/set scores.
        //  This may not even be necessary because session init with scores = 0
        session.setFoodScore(0.0);
        session.setHousingScore(0.0);
        session.setDependentScore(0.0);
        session.setZipCode(zipcode);
        ContextController.getStudent().setSession(session);
        session.setStudentId(ContextController.getStudent().getId());
        session.setStudentName(ContextController.getStudent().getUsername());
        ContextController.setSession(session);
        sessionService.saveSession(session);
        ContextController.questions = questions;
        mav.addObject("questions", questions);
        mav.addObject("answersDto", answersDto);
        mav.addObject("student", ContextController.getStudent());

        return mav;
    }
    
    

    @PostMapping("/survey/submit")
    public String validateSurvey(@ModelAttribute AnswersDTO answersDto, Model model) {

        double foodScore = 0;
        double totalPossibleFoodScore = 0;
        double housingScore = 0;
        double totalPossibleHousingScore = 0;
        double dependentScore = 0;
        double totalPossibleDependentScore = 0;

        int optionIndex = 0;
        String responseText = "";
        double responseValue = 0;

        List<Question> questions = ContextController.questions;

        ArrayList<Option> answers = new ArrayList<>();

        Integer[] submittedAnswers = answersDto.getAnswers();

        Session existingSession = sessionService.findSessionById(ContextController
                .getSession().getId());

        existingSession.setEnd_time(new Timestamp(System.currentTimeMillis()));

        // Calculate food, housing and dependent scores from survey responses
        for(int i = 0; i< questions.size(); i++) {

            if(questions.get(i).getFoodResource()) {

                optionIndex = submittedAnswers[i];
                responseText = questions.get(i).getOptions().get(optionIndex).getOptionTitle();
                responseValue = questions.get(i).getOptions().get(optionIndex).getValue();

                answers.add(new Option(responseText, responseValue));

                System.out.println("Question " + (i + i) + " response = " + responseText + " && value = " + responseValue + ". Added to foodScore");

                foodScore += responseValue;

                // NOTE: THIS ASSUMES THAT OPTION VALUE 1 IS MAX VALUE
                // TODO Create logic to determine highest value of all options?
                totalPossibleFoodScore += questions.get(i).getOptions().get(0).getValue();

            }

            if (questions.get(i).getHousingResource()) {

                optionIndex = submittedAnswers[i];
                responseText = questions.get(i).getOptions().get(optionIndex).getOptionTitle();
                responseValue = questions.get(i).getOptions().get(optionIndex).getValue();

                answers.add(new Option(responseText, responseValue));

                System.out.println("Question " + (i + i) + " response = " + responseText + " && value = " + responseValue + ". Added to housingScore");

                housingScore += responseValue;

            }

            if (questions.get(i).getDependentResource()) {

                optionIndex = submittedAnswers[i];
                responseText = questions.get(i).getOptions().get(optionIndex).getOptionTitle();
                responseValue = questions.get(i).getOptions().get(optionIndex).getValue();

                answers.add(new Option(responseText, responseValue));

                System.out.println("Question " + (i + i) + " response = " + responseText + " && value = " + responseValue + ". Added to dependentScore");

                dependentScore += responseValue;

            }

        }
        

        // Round scores up for better resource coverage
        foodScore = Math.ceil(foodScore);
        housingScore = Math.ceil(housingScore);
        dependentScore = Math.ceil(dependentScore);

        existingSession.setFoodScore(foodScore);
        System.out.println("Final Food score: " + foodScore);

        existingSession.setHousingScore(housingScore);
        System.out.println("Final Housing score: " + housingScore);

        existingSession.setDependentScore(dependentScore);
        System.out.println("Final Dependent score: " + dependentScore);

        // Retrieve resources for each category based on scores;
        List<Resource> foodResources = Utils.getSurveyResources(1, foodScore);
        List<Resource> housingResources = Utils.getSurveyResources(2, housingScore);
        List<Resource> dependentResources = Utils.getSurveyResources(3, dependentScore);

        // Compile all do list
        List<Resource> allResources = new ArrayList<>();
        allResources.addAll(foodResources);
        allResources.addAll(housingResources);
        allResources.addAll(dependentResources);

        // Add to model
        model.addAttribute("foodScore", foodScore);
        model.addAttribute("possibleFoodScore", totalPossibleFoodScore);
        model.addAttribute("foodResources", foodResources);

        model.addAttribute("housingScore", housingScore);
        model.addAttribute("possibleHousingScore", totalPossibleHousingScore);
        model.addAttribute("housingResources", housingResources);

        model.addAttribute("dependentScore", dependentScore);
        model.addAttribute("possibleDependentScore", totalPossibleDependentScore);
        model.addAttribute("dependentResources", dependentResources);

        model.addAttribute("allResources", allResources);

//        model.addAttribute("total", "Out of " + questions.size());
        model.addAttribute("student", ContextController.getStudent());

        sessionService.saveSession(existingSession);

        return "survey-results";

    }

}

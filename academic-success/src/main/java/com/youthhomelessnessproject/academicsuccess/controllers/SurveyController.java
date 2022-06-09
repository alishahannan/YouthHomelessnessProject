package com.youthhomelessnessproject.academicsuccess.controllers;

// TODO Implement this!!! This is where most of the business logic lies!

import com.youthhomelessnessproject.academicsuccess.services.QuestionService;
import com.youthhomelessnessproject.academicsuccess.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    // Runtime injection of QuestionService and SessionService dependencies
    private final QuestionService questionService;
    private final SessionService sessionService;

    @Autowired
    public SurveyController(QuestionService questionService, SessionService sessionService) {
        this.questionService = questionService;
        this.sessionService = sessionService;
    }

    // TODO MAKE THIS HAPPEN!!!

}

package com.youthhomelessnessproject.academicsuccess.services;

import com.youthhomelessnessproject.academicsuccess.models.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestionsByTag(String tag);

    Question saveQuestion(Question question);

    Question findQuestionById(Long id);

    void deleteQuestionById(Long id);
}
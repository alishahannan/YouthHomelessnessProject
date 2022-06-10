package com.youthhomelessnessproject.academicsuccess.services;

import com.youthhomelessnessproject.academicsuccess.models.Question;
import com.youthhomelessnessproject.academicsuccess.models.ResourceTag;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestionsByTags(ResourceTag tag);

    Question saveQuestion(Question question);

    Question findQuestionById(Long id);

    void deleteQuestionById(Long id);
}
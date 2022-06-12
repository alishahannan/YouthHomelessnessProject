package com.youthhomelessnessproject.academicsuccess.services;

import com.youthhomelessnessproject.academicsuccess.models.Question;
import com.youthhomelessnessproject.academicsuccess.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    // Runtime constructor-based injection of QuestionRepository dependency
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    @Override
    public List<Question> getAllFoodQuestions() {
        return questionRepository.findAllByFoodResourceIsTrue();
    }

    @Override
    public List<Question> getAllHousingQuestions() {
        return questionRepository.findAllByHousingResourceIsTrue();
    }

    @Override
    public List<Question> getAllDependentQuestions() {
        return questionRepository.findAllByDependentResourceIsTrue();
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question findQuestionById(Long id) {
        return questionRepository.findQuestionById(id);
    }

    @Override
    public void deleteQuestionById(Long id) {
//        questionRepository.deleteQuestionById(id);
        questionRepository.deleteById(id);
    }
}

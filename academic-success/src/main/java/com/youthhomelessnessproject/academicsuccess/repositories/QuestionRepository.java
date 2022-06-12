package com.youthhomelessnessproject.academicsuccess.repositories;

import com.youthhomelessnessproject.academicsuccess.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByFoodResourceIsTrue();

    List<Question> findAllByHousingResourceIsTrue();

    List<Question> findAllByDependentResourceIsTrue();

    Question findQuestionById(Long id);

    void deleteQuestionById(Long id);

    void deleteById(Long id);
}

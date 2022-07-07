package com.youthhomelessnessproject.academicsuccess.repositories;

import com.youthhomelessnessproject.academicsuccess.models.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
    Option findOptionById(Long id);

    List<Option> findOptionsByQuestionId(Long id);

    void deleteOptionById(Long id);
}

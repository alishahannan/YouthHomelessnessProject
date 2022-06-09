package com.youthhomelessnessproject.academicsuccess.repositories;

import com.youthhomelessnessproject.academicsuccess.models.SurveyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SurveyAdminRepository extends JpaRepository<SurveyAdmin, Long> {

    Boolean existsSurveyAdminByUsername(String username);

    SurveyAdmin findSurveyAdminByUsername(String username);

    void deleteSurveyAdminById(Long id);
}

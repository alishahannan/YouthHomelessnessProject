package com.youthhomelessnessproject.academicsuccess.services;

import com.youthhomelessnessproject.academicsuccess.models.Employee;
import com.youthhomelessnessproject.academicsuccess.models.SurveyAdmin;

import java.util.List;
import java.util.Optional;

public interface SurveyAdminService {
    // CREATE
    SurveyAdmin saveSurveyAdmin(SurveyAdmin surveyAdmin);

    // READ
    SurveyAdmin getSurveyAdminByUsername(String username);
    SurveyAdmin getSurveyAdminById(Long id);
    List<SurveyAdmin> getAllSurveyAdmins();

    // UPDATE
    SurveyAdmin updateSurveyAdmin(SurveyAdmin surveyAdmin);

    // DELETE
    void deleteSurveyAdminById(Long id);
}

package com.youthhomelessnessproject.academicsuccess.services;

import com.youthhomelessnessproject.academicsuccess.models.Employee;
import com.youthhomelessnessproject.academicsuccess.models.SurveyAdmin;
import com.youthhomelessnessproject.academicsuccess.repositories.SurveyAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyAdminServiceImpl implements SurveyAdminService {

    // Runtime constructor-based injection of TeacherRepository dependency
    private final SurveyAdminRepository surveyAdminRepository;

    @Autowired
    public SurveyAdminServiceImpl(SurveyAdminRepository surveyAdminRepository) {
        this.surveyAdminRepository = surveyAdminRepository;
    }

    @Override
    public SurveyAdmin saveSurveyAdmin(SurveyAdmin surveyAdmin) {
        return surveyAdminRepository.save(surveyAdmin);
    }

    @Override
    public SurveyAdmin getSurveyAdminByUsername(String username) {
        return surveyAdminRepository.findSurveyAdminByUsername(username);
    }

    @Override
    public SurveyAdmin getSurveyAdminById(Long id) {
        return surveyAdminRepository.findById(id).get();
    }

    @Override
    public List<SurveyAdmin> getAllSurveyAdmins() {
        return surveyAdminRepository.findAll();
    }

    @Override
    public SurveyAdmin updateSurveyAdmin(SurveyAdmin surveyAdmin) {
        return surveyAdminRepository.save(surveyAdmin);
    }

    @Override
    public void deleteSurveyAdminById(Long id) {
        surveyAdminRepository.deleteById(id);
    }
}

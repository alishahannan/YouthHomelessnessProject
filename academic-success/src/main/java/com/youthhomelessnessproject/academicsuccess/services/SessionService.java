package com.youthhomelessnessproject.academicsuccess.services;

import com.youthhomelessnessproject.academicsuccess.models.Session;

import java.util.List;
import java.util.Optional;

public interface SessionService {
    List<Session> getAllSessions();
    List<Session> getAllSessionsByStudentId(Long studentId);
    Session saveSession(Session session);
    Session findSessionById(Long id);

}

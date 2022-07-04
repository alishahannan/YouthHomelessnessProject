package com.youthhomelessnessproject.academicsuccess.controllers;

import com.youthhomelessnessproject.academicsuccess.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class ContextController {

    public static Student student;
    public static Admin admin;
    public static Employee employee;
    public static Session session;
    public static Survey survey;
    public static List<Question> questions;


    public static Student getStudent() { return student; }
    public static void setStudent(Student  student) { ContextController.student = student; }
    public static Admin getAdmin() { return admin; }
    public static void setAdmin(Admin admin) { ContextController.admin = admin; }
    public static Employee getEmployee() { return employee; }
    public static void setEmployee(Employee employee) { ContextController.employee = employee; }
    public static Session getSession() { return session; }
    public static void setSession(Session session) { ContextController.session = session; }
    public static Survey getSurvey() { return survey; }
    public static void setSurvey(Survey survey) { ContextController.survey = survey; }

}

package com.youthhomelessnessproject.academicsuccess.services;

import com.youthhomelessnessproject.academicsuccess.models.Admin;

import java.util.List;

public interface AdminService {

    // CREATE
    Admin saveAdmin(Admin admin);

    // READ
    Admin getAdminById(Long id);
    List<Admin> getAllAdmins();

    // DELETE
    void deleteAdminById(Long id);

}

package com.youthhomelessnessproject.academicsuccess.repositories;

import com.youthhomelessnessproject.academicsuccess.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Boolean existsAdminByUsername(String username);

    Admin findAdminByUsername(String username);

    Admin findAdminById(Long id);

    void deleteById(Long id);
}

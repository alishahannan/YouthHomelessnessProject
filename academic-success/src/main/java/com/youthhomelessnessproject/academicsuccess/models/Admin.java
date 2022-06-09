package com.youthhomelessnessproject.academicsuccess.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// Lombok
@Data
@NoArgsConstructor
//@AllArgsConstructor not used for Admins (for security)

// javax.persistence
@Entity
@Table(name = "tbl_admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String username;

    @NotBlank(message = "Password should not be empty")
    @Size(min = 5, message = "Password should be at least 5 characters long.")
    private String password;

    @Column(name="user_role")
    private String role = "administrator";

}

package com.youthhomelessnessproject.academicsuccess.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// Lombok
@Data // getters, setters, toString, hashCode, equals
@AllArgsConstructor
@NoArgsConstructor

// javax.persistence
@Entity
@Table(name = "tbl_employees")
public class Employee {

    @Id // javax.persistence
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    @NotBlank(message = "Please enter a username")
    @Size(min = 5, message = "Username must be at least 5 characters")
    private String username;

    @NotBlank(message = "Password should not be empty")
    @Size(min = 5, message = "Password should be at least 5 characters long.")
    private String password;

    @Column(name = "user_role")
    private String role = "employee";
    // TODO change this to employee...

}

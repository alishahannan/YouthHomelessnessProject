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
@Table(name = "tbl_students")
public class Student {

    @Id // javax.persistence
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Zip code cannot be blank")
    @Size(min = 5, max = 5, message = "Please enter a valid zip code")
    private String zip;
    // TODO implement create ZipCode entity with List of nearby zips to aid in resource management

    @Column(unique = true)
    @NotBlank(message = "Please enter a username")
    @Size(min = 5, message = "Username must be at least 5 characters")
    private String username;

    @NotBlank(message = "Password should not be empty")
    @Size(min = 5, max = 200, message = "Password should be at least 5 characters long.")
    private String password;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy ="student")
    private Session session;

    @Column(name = "user_role")
    private String role = "student";

}

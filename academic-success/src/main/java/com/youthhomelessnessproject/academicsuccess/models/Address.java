package com.youthhomelessnessproject.academicsuccess.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tbl_addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please enter a street address")
    private String street;

    private String city;

    private String state;

    @NotBlank(message = "Zip code cannot be blank")
    @Size(min = 5, max = 5, message = "Please enter a valid zip code")
    private String zip;

    private String phone;

    private String website;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "address")
    private Resource resource;

}

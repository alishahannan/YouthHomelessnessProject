package com.youthhomelessnessproject.academicsuccess.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tbl_resources")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @NotEmpty(message = "Please enter a numeric degree for this resource")
    private Double degree = 0.0;

    @NotBlank(message = "Please enter a name")
    private String name;

    @NotBlank(message = "Please enter a brief description")
    private String description;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Address address;

    // TODO make sure at least ONE is selected when creating a new Resource
    @Column(name = "food_resource")
    private Boolean foodResource;

    @Column(name = "housing_resource")
    private Boolean housingResource;

    @Column(name = "dependent_resource")
    private Boolean dependentResource;

}

package com.youthhomelessnessproject.academicsuccess.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "Please enter a name")
    private String name;

    @NotBlank(message = "Please enter a brief description")
    private String description;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(targetEntity = ResourceTag.class, mappedBy = "resource",
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ResourceTag> tags;

}

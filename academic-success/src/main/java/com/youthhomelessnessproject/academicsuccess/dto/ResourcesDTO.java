package com.youthhomelessnessproject.academicsuccess.dto;

import com.youthhomelessnessproject.academicsuccess.models.Address;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ResourcesDTO {

    private String name;
    private String description;
    private Address address;
    private Boolean foodResource;
    private Boolean housingResource;
    private Boolean dependentResource;
    private Double degree;
    private Long id;

    public ResourcesDTO(String name, String description, Address address,
                        Boolean foodResource, Boolean housingResource,
                        Boolean dependentResource, double degree) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.foodResource = foodResource;
        this.housingResource = housingResource;
        this.dependentResource = dependentResource;
        this.degree = degree;
    }

}

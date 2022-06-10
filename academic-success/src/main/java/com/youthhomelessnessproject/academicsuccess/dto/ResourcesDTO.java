package com.youthhomelessnessproject.academicsuccess.dto;

import com.youthhomelessnessproject.academicsuccess.models.Address;
import com.youthhomelessnessproject.academicsuccess.models.ResourceTag;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ResourcesDTO {

    private String name;
    private String description;
    private Address address;
    private List<ResourceTag> tags;
    private Long resourceId;

    public ResourcesDTO(String name, String description, Address address, List<ResourceTag> tags) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.tags = tags;
    }

}

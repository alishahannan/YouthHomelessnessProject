package com.youthhomelessnessproject.academicsuccess.services;

import com.youthhomelessnessproject.academicsuccess.models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceService {

    List<Resource> getAllResources();

    List<Resource> getAllFoodResources();

    List<Resource> getAllResourcesWithDegreeLessEqual(double degree);

    List<Resource> getAllFoodResourcesWithDegreeLessEqual(double degree);

    List<Resource> getAllHousingResources();

    List<Resource> getAllHousingResourcesWithDegreeLessEqual(double degree);

    List<Resource> getAllDependentResources();

    List<Resource> getAllDependentResourcesWithDegreeLessEqual(double degree);

    Resource saveResource(Resource resource);

    Resource findResourceById(Long id);

    void deleteResourceById(Long id);

}

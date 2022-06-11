package com.youthhomelessnessproject.academicsuccess.services;

import com.youthhomelessnessproject.academicsuccess.models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceService {

    List<Resource> getAllResources();

    List<Resource> getAllFoodResources();

    List<Resource> getAllFoodResourcesWithDegreeGreaterEqual(int degree);

    List<Resource> getAllHousingResources();

    List<Resource> getAllHousingResourcesWithDegreeGreaterEqual(int degree);

    List<Resource> getAllDependentResources();

    List<Resource> getAllDependentResourcesWithDegreeGreaterEqual(int degree);

    List<Resource> getAllResourcesWithDegreeGreaterThanEqual(int degree);

    Resource saveResource(Resource resource);

    Resource findResourceById(Long id);

    void deleteResourceById(Long id);

}

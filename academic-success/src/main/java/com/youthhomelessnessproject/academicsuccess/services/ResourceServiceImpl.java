package com.youthhomelessnessproject.academicsuccess.services;

import com.youthhomelessnessproject.academicsuccess.models.Resource;
import com.youthhomelessnessproject.academicsuccess.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    // Runtime constructor-based injection of ResourceRepository dependency
    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    @Override
    public List<Resource> getAllFoodResources() {
        return resourceRepository.findAllByFoodResourceIsTrue();
    }

    @Override
    public List<Resource> getAllFoodResourcesWithDegreeGreaterEqual(int degree) {
        return resourceRepository.findAllByFoodResourceIsTrueAndDegreeGreaterThanEqual(degree);
    }

    @Override
    public List<Resource> getAllHousingResources() {
        return resourceRepository.findAllByHousingResourceIsTrue();
    }

    @Override
    public List<Resource> getAllHousingResourcesWithDegreeGreaterEqual(int degree) {
        return resourceRepository.findAllByHousingResourceIsTrueAndDegreeGreaterThanEqual(degree);
    }

    @Override
    public List<Resource> getAllDependentResources() {
        return resourceRepository.findAllByDependentResourceIsTrue();
    }

    @Override
    public List<Resource> getAllDependentResourcesWithDegreeGreaterEqual(int degree) {
        return resourceRepository.findAllByDependentResourceIsTrueAndDegreeGreaterThanEqual(degree);
    }

    @Override
    public List<Resource> getAllResourcesWithDegreeGreaterThanEqual(int degree) {
        return resourceRepository.findAllByDegreeGreaterThanEqual(degree);
    }

    @Override
    public Resource saveResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    @Override
    public Resource findResourceById(Long id) {
        return resourceRepository.findResourceById(id);
    }

    @Override
    public void deleteResourceById(Long id) {
        resourceRepository.deleteResourceById(id);
    }
}

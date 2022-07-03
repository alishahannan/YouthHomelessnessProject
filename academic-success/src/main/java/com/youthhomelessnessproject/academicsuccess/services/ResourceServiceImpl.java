package com.youthhomelessnessproject.academicsuccess.services;

import com.youthhomelessnessproject.academicsuccess.models.Resource;
import com.youthhomelessnessproject.academicsuccess.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
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
    public List<Resource> getAllFoodResourcesWithDegreeLessEqual(double degree) {
        return resourceRepository.findAllByFoodResourceIsTrueAndDegreeLessThanEqual(degree);
    }

    @Override
    public List<Resource> getAllHousingResources() {
        return resourceRepository.findAllByHousingResourceIsTrue();
    }

    @Override
    public List<Resource> getAllHousingResourcesWithDegreeLessEqual(double degree) {
        return resourceRepository.findAllByHousingResourceIsTrueAndDegreeLessThanEqual(degree);
    }

    @Override
    public List<Resource> getAllDependentResources() {
        return resourceRepository.findAllByDependentResourceIsTrue();
    }

    @Override
    public List<Resource> getAllDependentResourcesWithDegreeLessEqual(double degree) {
        return resourceRepository.findAllByDependentResourceIsTrueAndDegreeLessThanEqual(degree);
    }

    @Override
    public List<Resource> getAllResourcesWithDegreeLessEqual(double degree) {
        return resourceRepository.findAllByDegreeLessThanEqual(degree);
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

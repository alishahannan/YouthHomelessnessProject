package com.youthhomelessnessproject.academicsuccess.services;

import com.youthhomelessnessproject.academicsuccess.models.Resource;
import com.youthhomelessnessproject.academicsuccess.models.ResourceTag;
import com.youthhomelessnessproject.academicsuccess.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    // Runtime constructor-based injection of ResourceRepository dependency
    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public List<Resource> getAllResourcesByTag(ResourceTag tag) {
        return resourceRepository.findResourcesByTags(tag);
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

package com.youthhomelessnessproject.academicsuccess.services;

import com.youthhomelessnessproject.academicsuccess.models.Resource;
import com.youthhomelessnessproject.academicsuccess.models.ResourceTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceService {

    List<Resource> getAllResourcesByTag(ResourceTag tag);

    Resource saveResource(Resource resource);

    Resource findResourceById(Long id);

    void deleteResourceById(Long id);

}

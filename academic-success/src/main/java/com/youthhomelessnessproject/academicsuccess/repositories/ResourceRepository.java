package com.youthhomelessnessproject.academicsuccess.repositories;

import com.youthhomelessnessproject.academicsuccess.models.Question;
import com.youthhomelessnessproject.academicsuccess.models.Resource;
import com.youthhomelessnessproject.academicsuccess.models.ResourceTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    List<Resource> findResourcesByTags(ResourceTag tag);

    Resource findResourceById(Long id);

    void deleteResourceById(Long id);
}

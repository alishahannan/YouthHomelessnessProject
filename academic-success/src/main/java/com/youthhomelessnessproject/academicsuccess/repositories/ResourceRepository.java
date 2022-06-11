package com.youthhomelessnessproject.academicsuccess.repositories;

import com.youthhomelessnessproject.academicsuccess.models.Question;
import com.youthhomelessnessproject.academicsuccess.models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    List<Resource> findAllByFoodResourceIsTrue();

    List<Resource> findAllByFoodResourceIsTrueAndDegreeGreaterThanEqual(int degree);

    List<Resource> findAllByHousingResourceIsTrue();

    List<Resource> findAllByHousingResourceIsTrueAndDegreeGreaterThanEqual(int degree);

    List<Resource> findAllByDependentResourceIsTrue();

    List<Resource> findAllByDependentResourceIsTrueAndDegreeGreaterThanEqual(int degree);

    List<Resource> findAllByDegreeGreaterThanEqual(int degree);

    Resource findResourceById(Long id);

    void deleteResourceById(Long id);
}

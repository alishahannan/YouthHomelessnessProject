package com.youthhomelessnessproject.academicsuccess.repositories;

import com.youthhomelessnessproject.academicsuccess.models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    List<Resource> findAllByFoodResourceIsTrue();

    List<Resource> findAllByFoodResourceIsTrueAndDegreeLessThanEqual(double degree);

    List<Resource> findAllByHousingResourceIsTrue();

    List<Resource> findAllByHousingResourceIsTrueAndDegreeLessThanEqual(double degree);

    List<Resource> findAllByDependentResourceIsTrue();

    List<Resource> findAllByDependentResourceIsTrueAndDegreeLessThanEqual(double degree);

    List<Resource> findAllByDegreeLessThanEqual(double degree);

    Resource findResourceById(Long id);

    void deleteResourceById(Long id);

}

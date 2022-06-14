package com.youthhomelessnessproject.academicsuccess.utils;

import com.youthhomelessnessproject.academicsuccess.models.Resource;
import com.youthhomelessnessproject.academicsuccess.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Utils {

    static ResourceService resourceService;

    public Utils(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public static double calculateNegativeMarks(int totalQuestions, int totalCorrect) {
        if(totalCorrect == 0) return 0;
        double negativeMark = 0.25;
        return (totalCorrect * 1) - ((totalQuestions - totalCorrect) * negativeMark);
    }

    public static List<Resource> getSurveyResources(int type, double score) {
        ArrayList<Resource> resources = new ArrayList<>();

        // 1 == Food, 2 == Housing, 3 == Dependent Resources
        if (type == 1) {
            resources.addAll(resourceService.getAllFoodResourcesWithDegreeLessEqual(score));
            return resources;
        } else if (type == 2) {
            resources.addAll(resourceService.getAllHousingResourcesWithDegreeLessEqual(score));
            return resources;
        } else if (type == 3) {
            resources.addAll(resourceService.getAllDependentResourcesWithDegreeLessEqual(score));
            return resources;
        }
        return null;
    }

}

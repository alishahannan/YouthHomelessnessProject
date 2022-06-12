package com.youthhomelessnessproject.academicsuccess.utils;

import com.youthhomelessnessproject.academicsuccess.models.Resource;
import com.youthhomelessnessproject.academicsuccess.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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

//    public static List<Resource> getSurveyResources(double score, int type) {
//        ArrayList<Resource> resources = new ArrayList<>();
//
//        // Food, Housing, Dependent Resources
//        if (type == 1) {
//            resources.addAll(resourceService.getAllFoodResourcesWithDegreeLessEqual(score));
//            return resources;
//        } else if (type == 2) {
//            resources.addAll(resourceService.getAllHousingResourcesWithDegreeLessEqual(score));
//            return resources;
//        } else if (type == 3) {
//            resources.addAll(resourceService.getAllDependentResourcesWithDegreeLessEqual(score));
//            return resources;
//        }
//        return null;
//    }

}

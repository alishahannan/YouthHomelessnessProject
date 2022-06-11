package com.youthhomelessnessproject.academicsuccess.dto;

import com.youthhomelessnessproject.academicsuccess.models.Option;

import java.util.List;

public class QuestionsDTO {

    private String questionTitle;
    private List<Option> options;
    private Boolean foodResource;
    private Boolean housingResource;
    private Boolean dependentResource;
    private Long questionId;

    public QuestionsDTO() {

    }

    public QuestionsDTO(String questionTitle, List<Option> options,
                        Boolean foodResource,
                        Boolean housingResource,
                        Boolean dependentResource) {
        super();
        this.questionTitle = questionTitle;
        this.options = options;
        this.foodResource = foodResource;
        this.housingResource = housingResource;
        this.dependentResource = dependentResource;
    }

    public String getQuestionTitle() { return questionTitle; }
    public void setQuestionTitle(String questionTitle) { this.questionTitle = questionTitle; }
    public List<Option> getQuestionOptions() { return options; }
    public void setQuestionOptions(List<Option> options) { this.options = options; }
    public Option getQuestionOptionByIndex(int index) { return options.get(index); }
    public void setQuestionOptionByIndex(int index, String optionTitle,
                                         double value) {
        options.get(index).setOptionTitle(optionTitle);
        options.get(index).setValue(value);
    };

    public Long getQuestionId() { return questionId; }
    public void setQuestionId(Long questionId) {this.questionId = questionId;}


    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Boolean getFoodResource() {
        return foodResource;
    }

    public void setFoodResource(Boolean foodResource) {
        this.foodResource = foodResource;
    }

    public Boolean getHousingResource() {
        return housingResource;
    }

    public void setHousingResource(Boolean housingResource) {
        this.housingResource = housingResource;
    }

    public Boolean getDependentResource() {
        return dependentResource;
    }

    public void setDependentResource(Boolean dependentResource) {
        this.dependentResource = dependentResource;
    }
}

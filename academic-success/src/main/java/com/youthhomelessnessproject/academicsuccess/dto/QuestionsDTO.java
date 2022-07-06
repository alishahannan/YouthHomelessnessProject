package com.youthhomelessnessproject.academicsuccess.dto;

import com.youthhomelessnessproject.academicsuccess.models.Option;

import java.util.List;

public class QuestionsDTO {

    private String title;
    private List<Option> options;
//    private String option1;
//    private Double option1Value;
//    private String option2;
//    private Double option2Value;
//    private String option3;
//    private Double option3Value;
//    private String option4;
//    private Double option4Value;
//    private String option5;
//    private Double option5Value;
    private Boolean foodResource;
    private Boolean housingResource;
    private Boolean dependentResource;
    private Long id;

    public QuestionsDTO() {

    }

    public QuestionsDTO(String title, List<Option> options,
                        Boolean foodResource,
                        Boolean housingResource,
                        Boolean dependentResource,
                        String option1, Double option1Value,
                        String option2, Double option2Value,
                        String option3, Double option3Value,
                        String option4, Double option4Value,
                        String option5, Double option5Value) {
        super();
        this.title = title;
        this.options = options;
        this.foodResource = foodResource;
        this.housingResource = housingResource;
        this.dependentResource = dependentResource;
//        this.option1 = option1;
//        this.option1Value = option1Value;
//        this.option2 = option2;
//        this.option2Value = option2Value;
//        this.option3 = option3;
//        this.option3Value = option3Value;
//        this.option4 = option4;
//        this.option4Value = option4Value;
//        this.option5 = option5;
//        this.option5Value = option5Value;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public List<Option> getQuestionOptions() { return options; }
    public void setQuestionOptions(List<Option> options) { this.options = options; }
    public Option getQuestionOptionByIndex(int index) { return options.get(index); }
    public void setQuestionOptionByIndex(int index, String optionTitle,
                                         double value) {
        options.get(index).setOptionTitle(optionTitle);
        options.get(index).setValue(value);
    };

    public Long getId() { return id; }
    public void setId(Long id) {this.id = id;}


    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public void addOption(Option option) { this.options.add(option); }

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

//    public String getOption1() {
//        return option1;
//    }
//
//    public void setOption1(String option1) {
//        this.option1 = option1;
//    }
//
//    public Double getOption1Value() {
//        return option1Value;
//    }
//
//    public void setOption1Value(Double option1Value) {
//        this.option1Value = option1Value;
//    }
//
//    public String getOption2() {
//        return option2;
//    }
//
//    public void setOption2(String option2) {
//        this.option2 = option2;
//    }
//
//    public Double getOption2Value() {
//        return option2Value;
//    }
//
//    public void setOption2Value(Double option2Value) {
//        this.option2Value = option2Value;
//    }
//
//    public String getOption3() {
//        return option3;
//    }
//
//    public void setOption3(String option3) {
//        this.option3 = option3;
//    }
//
//    public Double getOption3Value() {
//        return option3Value;
//    }
//
//    public void setOption3Value(Double option3Value) {
//        this.option3Value = option3Value;
//    }
//
//    public String getOption4() {
//        return option4;
//    }
//
//    public void setOption4(String option4) {
//        this.option4 = option4;
//    }
//
//    public Double getOption4Value() {
//        return option4Value;
//    }
//
//    public void setOption4Value(Double option4Value) {
//        this.option4Value = option4Value;
//    }
//
//    public String getOption5() {
//        return option5;
//    }
//
//    public void setOption5(String option5) {
//        this.option5 = option5;
//    }
//
//    public Double getOption5Value() {
//        return option5Value;
//    }
//
//    public void setOption5Value(Double option5Value) {
//        this.option5Value = option5Value;
//    }
}

package com.youthhomelessnessproject.academicsuccess.dto;

import com.youthhomelessnessproject.academicsuccess.models.ResourceTag;

import java.util.List;

public class QuestionsDTO {

    private String questionTitle;
    private List<String> options;
    private List<Double> optionValues;
    private List<ResourceTag> tags;
    private Long questionId;

    public QuestionsDTO() {

    }

    public QuestionsDTO(String questionTitle, List<String> options) {
        super();
        this.questionTitle = questionTitle;
        this.options = options;
    }

    public String getQuestionTitle() { return questionTitle; }
    public void setQuestionTitle(String questionTitle) { this.questionTitle = questionTitle; }
    public List<String> getOptions() { return options; }
    public void setQuestionOptions(List<String> options) { this.options = options; }
    public List<Double> getOptionValues() {return optionValues; }
    public void setOptionValues(List<Double> optionValues) {this.optionValues = optionValues; }
    public List<ResourceTag> getTags() { return tags; }
    public void setTags(List<ResourceTag> tags) { this.tags = tags; }
    public Long getQuestionId() { return questionId; }
    public void setQuestionId(Long questionId) {this.questionId = questionId;}

}

package com.youthhomelessnessproject.academicsuccess.dto;

import com.youthhomelessnessproject.academicsuccess.models.Option;
import com.youthhomelessnessproject.academicsuccess.models.ResourceTag;

import java.util.List;

public class QuestionsDTO {

    private String questionTitle;
    private List<Option> options;
    private List<ResourceTag> tags;
    private Long questionId;

    public QuestionsDTO() {

    }

    public QuestionsDTO(String questionTitle, List<Option> options, List<ResourceTag> tags) {
        super();
        this.questionTitle = questionTitle;
        this.options = options;
        this.tags = tags;
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
    public List<ResourceTag> getQuestionResourceTags() { return tags; }
    public void setQuestionResourceTags(List<ResourceTag> tags) { this.tags = tags; }
    public Long getQuestionId() { return questionId; }
    public void setQuestionId(Long questionId) {this.questionId = questionId;}

}

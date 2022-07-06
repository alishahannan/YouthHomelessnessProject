package com.youthhomelessnessproject.academicsuccess.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tbl_questions")
public class Question {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_title", nullable = false)
    private String title;

    @OneToMany(targetEntity = Option.class, mappedBy = "question",
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Option> options;

    @ManyToOne()
    @JoinColumn(name = "survey_id")
    private Survey survey;

    private double score = 0;

    private Boolean foodResource;
    private Boolean housingResource;
    private Boolean dependentResource;

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

}

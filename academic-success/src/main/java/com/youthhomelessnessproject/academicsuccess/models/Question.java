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

    @OneToMany(targetEntity = ResourceTag.class, mappedBy = "question",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ResourceTag> tags;

}

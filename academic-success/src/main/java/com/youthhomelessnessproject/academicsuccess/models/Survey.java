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
@Table(name = "tbl_surveys")
public class Survey {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long questionId;

    @OneToMany(targetEntity = Question.class, mappedBy = "survey",
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Question> questions;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "survey")
    private Session session;

    public Survey(List<Question> questions, Session session) {
        super();
        this.questions = questions;
        this.session = session;
    }

}

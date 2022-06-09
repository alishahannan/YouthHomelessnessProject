package com.youthhomelessnessproject.academicsuccess.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tbl_options")
public class Option {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "option_title", nullable = false)
    private String optionTitle;

    @ManyToOne()
    @JoinColumn(name = "question_id")
    private Question question;

    private double value;

}

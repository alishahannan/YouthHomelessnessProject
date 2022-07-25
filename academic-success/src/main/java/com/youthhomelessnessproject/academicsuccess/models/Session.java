package com.youthhomelessnessproject.academicsuccess.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tbl_sessions")
public class Session {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

//  // TODO check if JsonIgnore can be used here
//    @JsonIgnore           // when fetching session, hide user
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Student student;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "session_id")
    private Survey survey;

    @CreationTimestamp
    @Column(name = "start_time", nullable = false, updatable = false)
    private Timestamp start_time;

    @UpdateTimestamp
    @Column(name = "end_time")
    private Timestamp end_time;

    @Column(name="current_student_id")
    private Long studentId;

    // Used in SurveyController
    private String studentName;

    @Column(name = "food_score")
    private double foodScore = 0;

    @Column(name = "housing_score")
    private double housingScore = 0;

    @Column(name = "dependent_score")
    private double dependentScore = 0;
    
    @Column(name = "zipcode")
    private String zipCode;

    // TODO figure out how to tally scores!

}

package com.learnspootboot.LearnSpringBoot.Question.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;
    @Column
    private String questionText;
    @Column
    private String option1;
    @Column
    private String option2;
    @Column
    private String option3;
    @Column
    private String option4;
    @Column
    private String rightAnswer;
    @Column
    private String difficultyLevel;
    @Column
    private String category;
}

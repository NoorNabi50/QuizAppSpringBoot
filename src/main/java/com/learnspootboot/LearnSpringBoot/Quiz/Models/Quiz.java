package com.learnspootboot.LearnSpringBoot.Quiz.Models;


import com.learnspootboot.LearnSpringBoot.Question.Models.Question;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private Integer quizId;
    @Column(name = "title_text")
    private String titleText;
    @ManyToMany()
    private List<Question> questions; // a quiz may have multiple questions and vice versa
}

package com.learnspootboot.LearnSpringBoot.Question.ViewModels;

import jakarta.persistence.Column;

public record QuestionViewModel(
        Integer questionId,
        String questionText,
        String option1,
        String option2,
        String option3,
        String option4,
        String difficultyLevel,
        String category ) {}
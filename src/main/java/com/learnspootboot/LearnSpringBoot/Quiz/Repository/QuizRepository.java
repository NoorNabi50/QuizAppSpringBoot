package com.learnspootboot.LearnSpringBoot.Quiz.Repository;

import com.learnspootboot.LearnSpringBoot.Quiz.Models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Integer> {
}

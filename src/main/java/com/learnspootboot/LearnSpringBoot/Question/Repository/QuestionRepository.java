package com.learnspootboot.LearnSpringBoot.Question.Repository;

import com.learnspootboot.LearnSpringBoot.Question.Models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

    @Query("SELECT q FROM Question q where UPPER(q.category) = UPPER(:category)")
    List<Question> findByCategory(String category);

    @Query("SELECT q FROM Question q where UPPER(q.category) = UPPER(:category) ORDER BY RANDOM() Limit :noOfQuestions")
    List<Question> generateRandomQuestionsByCategory(String category,int noOfQuestions);
}

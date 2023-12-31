package com.learnspootboot.LearnSpringBoot.Quiz.Controllers;

import com.learnspootboot.LearnSpringBoot.Question.Models.Question;
import com.learnspootboot.LearnSpringBoot.Question.ViewModels.QuestionViewModel;
import com.learnspootboot.LearnSpringBoot.Quiz.Service.QuizService;
import com.learnspootboot.LearnSpringBoot.Quiz.ViewModels.QuizViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam("category") String categoryName, @RequestParam("title") String quizTitle, @RequestParam("totalQs") int noOfQuestions){
       return quizService.create(categoryName,quizTitle,noOfQuestions);
    }
    @GetMapping(value = "getQuizQuestions/{quizId}")
    public ResponseEntity<List<QuestionViewModel>> getQuestionsByQuizId(@PathVariable int quizId) {
        return quizService.getQuestionsByQuizId(quizId);
    }
    @PostMapping(value = "submitQuiz/{quizId}")
    public ResponseEntity<BigDecimal> submitQuiz(@PathVariable int quizId, @RequestBody List<QuizViewModel> submittedAnswers){
        return quizService.calculateQuizResult(quizId,submittedAnswers);
    }
}

package com.learnspootboot.LearnSpringBoot.Question.Controllers;

import com.learnspootboot.LearnSpringBoot.Question.Models.Question;
import com.learnspootboot.LearnSpringBoot.Question.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping("save")
    public ResponseEntity<String> addQuestion(@RequestBody List<Question> questions){
        return questionService.addQuestions(questions);
    }
    @GetMapping("getAllQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }
    @GetMapping("getQuestionByCategory/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable("category") String categoryName){
         return questionService.getQuestionsByCategory(categoryName);
    }
}

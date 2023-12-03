package com.learnspootboot.LearnSpringBoot.Question.Service;

import com.learnspootboot.LearnSpringBoot.Question.Models.Question;
import com.learnspootboot.LearnSpringBoot.Question.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    public ResponseEntity<List<Question>>  getAllQuestions(){
         List<Question> questions = new ArrayList<>();
        try{
            questions = questionRepository.findAll();
        }
        catch (Exception e){
               e.printStackTrace();
        }
        return ResponseEntity.ok(questions);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        List<Question> questions = new ArrayList<>();
        try{
            questions = questionRepository.findByCategory(category);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(questions);
    }

    public ResponseEntity<String> addQuestions(List<Question> questions) {
        String response = "Failed";
        try{
               boolean isSuccess =  questionRepository.saveAll(questions).stream()
                                                      .allMatch(question -> question.getQuestionId() > 0);
               if(isSuccess)
                   return new ResponseEntity<>("Saved",HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }
}

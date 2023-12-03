package com.learnspootboot.LearnSpringBoot.Quiz.Service;


import com.learnspootboot.LearnSpringBoot.Question.Models.Question;
import com.learnspootboot.LearnSpringBoot.Question.Repository.QuestionRepository;
import com.learnspootboot.LearnSpringBoot.Question.Service.QuestionService;
import com.learnspootboot.LearnSpringBoot.Question.ViewModels.QuestionViewModel;
import com.learnspootboot.LearnSpringBoot.Quiz.Models.Quiz;
import com.learnspootboot.LearnSpringBoot.Quiz.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;
    public ResponseEntity<String> create(String categoryName, String quizTitle, int noOfQuestions) {
        try{
            List<Question> questionList = questionRepository.generateRandomQuestionsByCategory(categoryName, noOfQuestions);
            Quiz quiz = new Quiz();
            quiz.setTitleText(quizTitle);
            quiz.setQuestions(questionList);
            if(quizRepository.save(quiz).getQuizId() > 0)
                new ResponseEntity<>("Created", HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
        }
         return new ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<QuestionViewModel>> getQuestionsByQuizId(int quizId){
        List<QuestionViewModel> questions = new ArrayList<>();
        try{
            Optional<Quiz> quiz =  quizRepository.findById(quizId);
             List<Question> questionList = quiz.get().getQuestions();
             if(questionList != null && questionList.size() > 0){
                    questionList.stream().forEach(question -> {
                        QuestionViewModel questionViewModel = new QuestionViewModel(question.getQuestionText(),question.getOption1(),question.getOption2(),question.getOption3(),
                                question.getOption4(),question.getDifficultyLevel(),question.getCategory());
                        questions.add(questionViewModel);
                    });
                }
        }
        catch (Exception e){
          e.printStackTrace();
        }
       return ResponseEntity.ok(questions);
    }
}

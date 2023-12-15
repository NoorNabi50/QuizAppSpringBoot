package com.learnspootboot.LearnSpringBoot.Quiz.Service;


import com.learnspootboot.LearnSpringBoot.Question.Models.Question;
import com.learnspootboot.LearnSpringBoot.Question.Repository.QuestionRepository;
import com.learnspootboot.LearnSpringBoot.Question.Service.QuestionService;
import com.learnspootboot.LearnSpringBoot.Question.ViewModels.QuestionViewModel;
import com.learnspootboot.LearnSpringBoot.Quiz.Models.Quiz;
import com.learnspootboot.LearnSpringBoot.Quiz.Repository.QuizRepository;
import com.learnspootboot.LearnSpringBoot.Quiz.ViewModels.QuizViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
            quizRepository.save(quiz).getQuizId();
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
                        QuestionViewModel questionViewModel = new QuestionViewModel(question.getQuestionId(),question.getQuestionText(),question.getOption1(),question.getOption2(),question.getOption3(),
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

    public  ResponseEntity<BigDecimal> calculateQuizResult(int quizId, List<QuizViewModel> submittedAnswers){
        BigDecimal result = new BigDecimal(0.0);
        int correctAnswers = 0 ;
        try{
            if(submittedAnswers == null || submittedAnswers.size()==0)
                return ResponseEntity.ok(result);
            Optional<Quiz> quiz = quizRepository.findById(quizId);
            List<Question> questions = quiz.get().getQuestions();
            for(Question question : questions){
                QuizViewModel quizViewModel =  submittedAnswers.stream().
                                                                      filter(answer -> answer.questionId() == question.getQuestionId()).
                                                                      findFirst().orElse(null);
                if(quizViewModel !=null && question.getRightAnswer().equals(quizViewModel.chosenAnswer())){
                         correctAnswers++;
                }
            }
            BigDecimal correctAnswersBigDecimal = BigDecimal.valueOf(correctAnswers);
            BigDecimal submittedAnswersBigDecimal = BigDecimal.valueOf(submittedAnswers.size());
            BigDecimal hundred = BigDecimal.valueOf(100);
            result = correctAnswersBigDecimal.multiply(hundred).divide(submittedAnswersBigDecimal,2, RoundingMode.HALF_UP);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }
}

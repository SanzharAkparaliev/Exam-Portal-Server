package com.example.examserver.controller;

import com.example.examserver.model.exam.Category;
import com.example.examserver.model.exam.Quiz;
import com.example.examserver.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
        Quiz quiz1 = quizService.addQuiz(quiz);
        return ResponseEntity.ok(quiz1);
    }

    @PutMapping("/")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz){
        return ResponseEntity.ok(quizService.updateQuiz(quiz));
    }

    @GetMapping("/")
    public ResponseEntity<?> getQuizes(){
        return ResponseEntity.ok(quizService.getQuizes());
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<?> getQuiz(@PathVariable("quizId") Long quizId){
        return ResponseEntity.ok(quizService.getQuiz(quizId));
    }

    @DeleteMapping("/{quizId}")
    public void deleteQuiz(@PathVariable("quizId") Long qId){
        quizService.deleteQuiz(qId);
    }

    @GetMapping("/category/{cid}")
    public List<Quiz> getQuizOfCategory(@PathVariable("cid")Long cid){
        Category category = new Category();
        category.setCId(cid);
        return quizService.getQuizOfCategory(category);
    }

}

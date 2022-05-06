package com.example.examserver.service;

import com.example.examserver.model.exam.Category;
import com.example.examserver.model.exam.Quiz;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface QuizService {
    public Quiz addQuiz(Quiz quiz);

    public Quiz getQuiz(Long quizId);

    public Quiz updateQuiz(Quiz quiz);

    public Set<Quiz> getQuizes();

    public void deleteQuiz(Long quizId);

    public List<Quiz> getQuizOfCategory(Category category);
}

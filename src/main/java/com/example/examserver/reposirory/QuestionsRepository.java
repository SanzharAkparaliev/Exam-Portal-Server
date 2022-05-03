package com.example.examserver.reposirory;

import com.example.examserver.model.exam.Question;
import com.example.examserver.model.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuestionsRepository extends JpaRepository<Question,Long> {
    Set<Question> findByQuiz(Quiz quiz);
}

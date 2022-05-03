package com.example.examserver.serviceimpl;

import com.example.examserver.model.exam.Question;
import com.example.examserver.model.exam.Quiz;
import com.example.examserver.reposirory.QuestionsRepository;
import com.example.examserver.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionsRepository questionsRepository;
    @Override
    public Question addQuestion(Question question) {
        return questionsRepository.save(question);
    }

    @Override
    public Question getQuestion(Long questionId) {
        return questionsRepository.findById(questionId).get();
    }

    @Override
    public Question updateQuestion(Question question) {
        return questionsRepository.save(question);
    }

    @Override
    public Set<Question> getQuestions() {
        return new LinkedHashSet<>(questionsRepository.findAll());
    }

    @Override
    public void deleteQuestion(Long questionId) {
        questionsRepository.deleteById(questionId);
    }

    @Override
    public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
        return questionsRepository.findByQuiz(quiz);
    }
}

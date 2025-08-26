package com.projects.quiz_service.dao;

import com.projects.quiz_service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
public interface QuizDao extends JpaRepository<Quiz,Integer> {
}

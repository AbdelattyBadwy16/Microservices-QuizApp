package com.projects.quiz_service.model;

import lombok.Data;

@Data
public class QuizDto {
    String title;
    Integer numQuestions;
    String categoryName;
}

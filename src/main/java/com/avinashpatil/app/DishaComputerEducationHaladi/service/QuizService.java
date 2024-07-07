package com.avinashpatil.app.DishaComputerEducationHaladi.service;

import com.avinashpatil.app.DishaComputerEducationHaladi.dtos.*;
import com.avinashpatil.app.DishaComputerEducationHaladi.entity.QuizResult;

import java.util.List;

public interface QuizService {
    List<QuizResponseDTO> generateQuiz(QuizRequestDTO quizRequestDTO);
    QuizResultDTO submitQuiz(List<UserAnswerDTO> answers);
}


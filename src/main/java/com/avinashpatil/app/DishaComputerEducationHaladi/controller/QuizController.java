package com.avinashpatil.app.DishaComputerEducationHaladi.controller;

import com.avinashpatil.app.DishaComputerEducationHaladi.dtos.*;
import com.avinashpatil.app.DishaComputerEducationHaladi.entity.QuizResult;
import com.avinashpatil.app.DishaComputerEducationHaladi.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/generate")
    public ResponseEntity<List<QuizResponseDTO>> generateQuiz(@RequestBody QuizRequestDTO quizRequestDTO) {
        List<QuizResponseDTO> quiz = quizService.generateQuiz(quizRequestDTO);
        return ResponseEntity.ok(quiz);
    }

    @PostMapping("/submit")
    public ResponseEntity<QuizResultDTO> submitQuiz(@RequestBody List<UserAnswerDTO> userAnswers) {
        QuizResultDTO result = quizService.submitQuiz(userAnswers);
        return ResponseEntity.ok(result);
    }
}


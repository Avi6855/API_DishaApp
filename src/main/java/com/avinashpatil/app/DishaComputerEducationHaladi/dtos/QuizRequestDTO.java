package com.avinashpatil.app.DishaComputerEducationHaladi.dtos;

import com.avinashpatil.app.DishaComputerEducationHaladi.helper.DifficultyLevel;
import com.avinashpatil.app.DishaComputerEducationHaladi.helper.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizRequestDTO {
    private String categoryName;
    private String subcategoryName;
    private int numberOfQuestions;
    private DifficultyLevel difficulty;
    private QuestionType type;
    private List<AnswerDTO> options;
}

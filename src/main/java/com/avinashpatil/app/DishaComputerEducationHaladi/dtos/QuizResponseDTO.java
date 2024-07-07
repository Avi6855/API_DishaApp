package com.avinashpatil.app.DishaComputerEducationHaladi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizResponseDTO {
    private Long questionId;
    private String questionEn;
    private String questionMr;
    private List<AnswerDTO> options;
}


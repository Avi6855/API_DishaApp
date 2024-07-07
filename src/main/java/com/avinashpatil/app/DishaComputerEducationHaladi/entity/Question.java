package com.avinashpatil.app.DishaComputerEducationHaladi.entity;


import com.avinashpatil.app.DishaComputerEducationHaladi.helper.DifficultyLevel;
import com.avinashpatil.app.DishaComputerEducationHaladi.helper.QuestionType;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionEn;
    private String questionMr;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DifficultyLevel difficultyLevel;

    private String correctAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory_id")
    private QuizSubCategory subcategory;

    @OneToMany(fetch =FetchType.LAZY, mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;
}



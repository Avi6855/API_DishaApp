package com.avinashpatil.app.DishaComputerEducationHaladi.repositories;


import  com.avinashpatil.app.DishaComputerEducationHaladi.entity.Question;
import com.avinashpatil.app.DishaComputerEducationHaladi.entity.QuizSubCategory;
import com.avinashpatil.app.DishaComputerEducationHaladi.helper.DifficultyLevel;
import com.avinashpatil.app.DishaComputerEducationHaladi.helper.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findBySubcategoryAndDifficultyLevelAndType(QuizSubCategory subcategory, DifficultyLevel difficulty, QuestionType type);
}

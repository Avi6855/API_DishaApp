package com.avinashpatil.app.DishaComputerEducationHaladi.repositories;

import com.avinashpatil.app.DishaComputerEducationHaladi.entity.QuizCategory;
import com.avinashpatil.app.DishaComputerEducationHaladi.entity.QuizSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizSubCategoryRepository extends JpaRepository<QuizSubCategory,Long> {
    Optional<QuizSubCategory> findByNameAndCategory(String name, QuizCategory category);
}

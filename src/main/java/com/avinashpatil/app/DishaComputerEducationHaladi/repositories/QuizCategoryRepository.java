package com.avinashpatil.app.DishaComputerEducationHaladi.repositories;


import com.avinashpatil.app.DishaComputerEducationHaladi.entity.QuizCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizCategoryRepository extends JpaRepository<QuizCategory, Long> {
    Optional<QuizCategory> findByName(String name);
}

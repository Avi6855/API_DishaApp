package com.avinashpatil.app.DishaComputerEducationHaladi.service.Impl;

import com.avinashpatil.app.DishaComputerEducationHaladi.dtos.*;
import com.avinashpatil.app.DishaComputerEducationHaladi.entity.*;
import com.avinashpatil.app.DishaComputerEducationHaladi.exceptions.ResourceNotFoundException;
import com.avinashpatil.app.DishaComputerEducationHaladi.repositories.*;
import com.avinashpatil.app.DishaComputerEducationHaladi.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizCategoryRepository quizCategoryRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizResultRepository quizResultRepository;

    @Autowired
    private QuizSubCategoryRepository quizSubcategoryRepository;

    @Autowired
    private AnswerRepository answerRepository;



    @Override
    public List<QuizResponseDTO> generateQuiz(QuizRequestDTO quizRequestDTO) {
        QuizCategory category = quizCategoryRepository.findByName(quizRequestDTO.getCategoryName())
                .orElseThrow(() -> new ResourceNotFoundException("Subcategory not found"));

        QuizSubCategory subcategory = quizSubcategoryRepository.findByNameAndCategory(quizRequestDTO.getSubcategoryName(), category)
                .orElseThrow(() -> new ResourceNotFoundException("Subcategory not found"));


        List<Question> allQuestions = questionRepository.findBySubcategoryAndDifficultyLevelAndType(subcategory, quizRequestDTO.getDifficulty(), quizRequestDTO.getType());

        if (allQuestions.isEmpty()) {
            throw new RuntimeException("No questions found for the specified subcategory");
        }

        Collections.shuffle(allQuestions);

        List<Question> selectedQuestions = allQuestions.stream()
                .limit(10)
                .collect(Collectors.toList());


        return selectedQuestions.stream().map(question -> {
            QuizResponseDTO quizResponseDTO = new QuizResponseDTO();
            quizResponseDTO.setQuestionId(question.getId());
            quizResponseDTO.setQuestionEn(question.getQuestionEn());
            quizResponseDTO.setQuestionMr(question.getQuestionMr());

            // Fetch answers (options) for the question
            List<AnswerDTO> options = question.getAnswers().stream().map(answer -> {
                AnswerDTO answerDTO = new AnswerDTO();
                answerDTO.setId(answer.getId());
                answerDTO.setAnswerEn(answer.getAnswerEn());
                answerDTO.setAnswerMr(answer.getAnswerMr());
                return answerDTO;
            }).collect(Collectors.toList());

            // Shuffle options to randomize the order
            Collections.shuffle(options);
            quizResponseDTO.setOptions(options);

            return quizResponseDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public QuizResultDTO submitQuiz(List<UserAnswerDTO> userAnswers) {
        int totalQuestions = userAnswers.size();
        int correctAnswers = 0;
        List<QuestionResultDTO> questionResults = new ArrayList();

        for (UserAnswerDTO userAnswer : userAnswers) {
            Long questionId = userAnswer.getQuestionId();
            String selectedAnswer = userAnswer.getSelectedAnswer();

            // Retrieve the question from database
            Optional<Question> questionOptional = questionRepository.findById(questionId);
            if (questionOptional.isPresent()) {
                Question question = questionOptional.get();
                // Check if selected answer matches the correct answer
                boolean isCorrect = selectedAnswer.equals(question.getCorrectAnswer());
                if (isCorrect) {
                    correctAnswers++;
                }
                QuestionResultDTO questionResultDTO = new QuestionResultDTO(
                        questionId,
                        question.getQuestionEn(),
                        question.getQuestionMr(),
                        question.getCorrectAnswer(),
                        selectedAnswer,
                        isCorrect
                );
                questionResults.add(questionResultDTO);
            }
        }

        // Prepare result DTO
        QuizResultDTO resultDTO = new QuizResultDTO();
        resultDTO.setTotalQuestions(totalQuestions);
        resultDTO.setCorrectAnswers(correctAnswers);
        resultDTO.setIncorrectAnswers(totalQuestions - correctAnswers);
        resultDTO.setQuestionResults(questionResults);

        // You can add more details to the result if needed (quiz name, category, subcategory, etc.)

        return resultDTO;
    }
}

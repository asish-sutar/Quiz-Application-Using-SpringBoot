package com.crud.controller;

/*
 Postmapping
 {
  "question": "What is the capital of France?",
  "options": ["Berlin", "Madrid", "Paris", "Rome"],
  "rightAnswer": 2,
  "startDate": "2023-01-01T10:00:00",
  "endDate": "2023-01-01T11:00:00",
  "status": "ACTIVE"
}
GetAllQuizes: http://localhost:5050/api/getAll
 */
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.model.Quiz;
import com.crud.model.QuizStatus;
import com.crud.service.QuizService;

@RestController
@RequestMapping("/api")
public class QuizController {
	@Autowired
	private QuizService quizService;

	@PostMapping
	public ResponseEntity<Quiz> createQuizzes(@RequestBody Quiz quiz) {
		quiz.setStatus(calculateQuizStatus(quiz));
		Quiz createdQuiz = quizService.createQuizes(quiz);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdQuiz);
	}

	private QuizStatus calculateQuizStatus(Quiz quiz) {
		LocalDateTime now = LocalDateTime.now();

		if (now.isBefore(quiz.getStartDate())) {
			System.out.println("inactive");
			return QuizStatus.INACTIVE; // Quiz hasn't started
		} else if (now.isAfter(quiz.getEndDate())) {
			System.out.println("finished");
			return QuizStatus.FINISHED; // Quiz has ended
		} else {
			System.out.println("active");
			return QuizStatus.ACTIVE; // Quiz is currently in active mode
		}
	}

	@GetMapping("/active")
	public ResponseEntity<List<Quiz>> getAllActiveQuizes() {
		List<Quiz> activeQuizes = quizService.getActiveQuizes();
		return ResponseEntity.ok(activeQuizes);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Quiz>> getAllQuizes() {
		List<Quiz> getAllQuizes = quizService.getAllQuizes();
		return ResponseEntity.ok(getAllQuizes);
	}

	@GetMapping("result/{id}")
	public ResponseEntity<String> getQuizResult(@PathVariable Integer id) {
		Optional<Quiz> quizById = quizService.getQuizById(id);
		if (quizById.isPresent()) {
			Quiz quiz = quizById.get();
			if (quizHasEnded(quiz)) {
				String result = calculateQuizResult(quiz);
				return ResponseEntity.ok(result);
			} else {
				return ResponseEntity.badRequest().body("Quiz is in active mode");
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	private boolean quizHasEnded(Quiz quiz) {
		return LocalDateTime.now().isAfter(quiz.getEndDate());
	}

	private String calculateQuizResult(Quiz quiz) {
		List<Integer> correctAnswers = Arrays.asList(1, 0, 2);
		int totalQuestions = quiz.getOptions().size();
		int correctCount = 0;
		for (int i = 0; i < totalQuestions; i++) {
			if (i == quiz.getRightAnswer() && correctAnswers.get(i) == i) {
				correctCount++;
			}
		}
		double percentage = ((double) correctCount / totalQuestions) * 100;
		return String.format("Quiz Result: %.2f%%", percentage);
	}

}

package com.crud.quizserviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.model.Quiz;
import com.crud.model.QuizStatus;
import com.crud.repository.QuizRepo;
import com.crud.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {
	@Autowired
	private QuizRepo quizRepo;

	@Override
	public List<Quiz> getAllQuizes() {
		return quizRepo.findAll();
	}

	@Override
	public Optional<Quiz> getQuizById(Integer id) {
		return quizRepo.findById(id);
	}

	@Override
	public List<Quiz> getActiveQuizes() {
		LocalDateTime dateTime = LocalDateTime.now();
		return quizRepo.findByStartDateBeforeAndEndDateAfter(dateTime, dateTime);
	}

	@Override
	public Quiz createQuizes(Quiz quiz) {
		quiz.setStatus(calculateQuizStatus(quiz));
		return quizRepo.save(quiz);
	}

	@Override
	public QuizStatus calculateQuizStatus(Quiz quiz) {
		LocalDateTime now = LocalDateTime.now();
		if (now.isBefore(quiz.getStartDate())) {
			return QuizStatus.INACTIVE;
		} else if (now.isAfter(quiz.getEndDate())) {
			return QuizStatus.FINISHED;
		} else {
			return QuizStatus.ACTIVE;
		}
	}
}

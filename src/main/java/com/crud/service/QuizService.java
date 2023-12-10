package com.crud.service;

import java.util.List;
import java.util.Optional;

import com.crud.model.Quiz;
import com.crud.model.QuizStatus;

public interface QuizService {

	public List<Quiz> getAllQuizes();

	public Optional<Quiz> getQuizById(Integer id);

	public List<Quiz> getActiveQuizes();

	public Quiz createQuizes(Quiz quiz);

	public QuizStatus calculateQuizStatus(Quiz quiz);

}

package com.crud.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.model.Quiz;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Integer> {

	List<Quiz> findByStartDateBeforeAndEndDateAfter(LocalDateTime startDate, LocalDateTime endDate);

	List<Quiz> findByEndDateBefore(LocalDateTime endDate);
}

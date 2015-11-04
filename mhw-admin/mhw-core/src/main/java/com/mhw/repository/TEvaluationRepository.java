package com.mhw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhw.entity.TEvaluation;

@Repository
public interface TEvaluationRepository extends JpaRepository<TEvaluation, Long> {
	
}

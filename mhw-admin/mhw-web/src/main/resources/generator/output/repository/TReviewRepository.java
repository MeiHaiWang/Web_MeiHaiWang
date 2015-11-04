package com.mhw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhw.entity.TReview;

@Repository
public interface TReviewRepository extends JpaRepository<TReview, Long> {
	
}

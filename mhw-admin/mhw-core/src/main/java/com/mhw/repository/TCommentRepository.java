package com.mhw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhw.entity.TComment;

@Repository
public interface TCommentRepository extends JpaRepository<TComment, Long> {
	
}

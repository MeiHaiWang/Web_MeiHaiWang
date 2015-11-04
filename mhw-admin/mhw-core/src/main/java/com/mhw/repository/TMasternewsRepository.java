package com.mhw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhw.entity.TMasternews;

@Repository
public interface TMasternewsRepository extends JpaRepository<TMasternews, Long> {
	
}

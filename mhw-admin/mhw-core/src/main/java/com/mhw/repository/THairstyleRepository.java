package com.mhw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhw.entity.THairstyle;

@Repository
public interface THairstyleRepository extends JpaRepository<THairstyle, Long> {
	
}

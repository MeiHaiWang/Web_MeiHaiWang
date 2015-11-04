package com.mhw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhw.entity.TMasterrecommend;

@Repository
public interface TMasterrecommendRepository extends JpaRepository<TMasterrecommend, Long> {
	
}

package com.mhw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhw.entity.TMasterarea;

@Repository
public interface TMasterareaRepository extends JpaRepository<TMasterarea, Long> {
	
}

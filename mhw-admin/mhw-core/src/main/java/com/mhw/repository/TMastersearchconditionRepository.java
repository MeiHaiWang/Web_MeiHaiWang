package com.mhw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhw.entity.TMastersearchcondition;

@Repository
public interface TMastersearchconditionRepository extends JpaRepository<TMastersearchcondition, Long> {
	
}

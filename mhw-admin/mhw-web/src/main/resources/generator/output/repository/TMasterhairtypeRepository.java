package com.mhw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhw.entity.TMasterhairtype;

@Repository
public interface TMasterhairtypeRepository extends JpaRepository<TMasterhairtype, Long> {
	
}

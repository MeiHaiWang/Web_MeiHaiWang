package com.mhw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhw.entity.TMastersearchconditiontitle;

@Repository
public interface TMastersearchconditiontitleRepository extends JpaRepository<TMastersearchconditiontitle, Long> {
	
}

package com.mhw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhw.entity.TImage;

@Repository
public interface TImageRepository extends JpaRepository<TImage, Long> {
	
}

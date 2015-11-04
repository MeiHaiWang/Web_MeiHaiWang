package com.mhw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhw.entity.TClaim;

@Repository
public interface TClaimRepository extends JpaRepository<TClaim, Long> {
	
}

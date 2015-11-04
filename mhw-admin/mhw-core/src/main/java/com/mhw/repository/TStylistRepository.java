package com.mhw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhw.entity.TStylist;

@Repository
public interface TStylistRepository extends JpaRepository<TStylist, Long> {
	
}

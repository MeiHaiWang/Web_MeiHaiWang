package com.mhw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhw.entity.TMastercouponkind;

@Repository
public interface TMastercouponkindRepository extends JpaRepository<TMastercouponkind, Long> {
	
}

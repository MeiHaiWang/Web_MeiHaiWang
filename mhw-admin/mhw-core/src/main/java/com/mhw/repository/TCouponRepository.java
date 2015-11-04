package com.mhw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhw.entity.TCoupon;

@Repository
public interface TCouponRepository extends JpaRepository<TCoupon, Long> {
	
}

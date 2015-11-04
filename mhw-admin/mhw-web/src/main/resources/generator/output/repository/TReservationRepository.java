package com.mhw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhw.entity.TReservation;

@Repository
public interface TReservationRepository extends JpaRepository<TReservation, Long> {
	
}

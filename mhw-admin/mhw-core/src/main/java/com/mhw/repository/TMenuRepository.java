package com.mhw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhw.entity.TMenu;

@Repository
public interface TMenuRepository extends JpaRepository<TMenu, Long> {
	
}

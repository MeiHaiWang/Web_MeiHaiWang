package com.mhw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhw.entity.TUser;

@Repository
public interface TUserRepository extends JpaRepository<TUser, Long> {
	
}

package com.hdb.masquito.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdb.masquito.model.AdminDTO;

public interface AdminDAO extends JpaRepository<AdminDTO, Integer>{
	
}

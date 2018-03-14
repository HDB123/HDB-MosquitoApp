package com.hdb.masquito.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdb.masquito.model.ResourceDTO;

public interface ResourceDAO extends JpaRepository<ResourceDTO,String> {


	ResourceDTO findByempName(String emp);
}

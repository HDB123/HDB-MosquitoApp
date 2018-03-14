package com.hdb.masquito.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdb.masquito.model.ProjectResourceDTO;

public interface ProjectResourceDAO  extends JpaRepository<ProjectResourceDTO, Integer>{

}

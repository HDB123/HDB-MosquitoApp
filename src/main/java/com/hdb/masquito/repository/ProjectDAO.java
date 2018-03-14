package com.hdb.masquito.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.hdb.masquito.model.ProjectDTO;

public interface ProjectDAO extends JpaRepository<ProjectDTO, Integer> {
	@Modifying
	@Query("update ProjectDTO u set u.projectName = ?1 where u.id = ?2")
	int setProjectNameFor(String projectname, int id);

	@Modifying
	@Query("update ProjectDTO u set u.projectType = ?1 where u.id = ?2")
	int setProjectTypeFor(String projectType, int id);

	@Modifying
	@Query("update ProjectDTO u set u.projectCode = ?1 where u.id = ?2")
	int setProjectCodeFor(String projectCode, int id);

	@Modifying
	@Query("update ProjectDTO u set u.startDate = ?1 where u.id = ?2")
	int setProjectStartDateFor(Date projectCode, int id);

	@Modifying
	@Query("update ProjectDTO u set u.projectDescription = ?1 where u.id = ?2")
	int setProjectDescFor(String projectDescription, int id);

	@Modifying
	@Query("update ProjectDTO u set u.ETA = ?1 where u.id = ?2")
	int setProjectETAFor(Date ETA, int id);

	@Modifying
	@Query("update ProjectDTO u set u.projectStatus = ?1 where u.id = ?2")
	int setProjectprojectStatusFor(String projectStatus, int id);

}

package com.hdb.masquito.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hdb.masquito.model.ProjectDTO;
import com.hdb.masquito.repository.ProjectDAO;
@Service
public class ProjectService {
	@Autowired
	private ProjectDAO dao;
	
	public void saveProject(ProjectDTO dto) {
		dao.save(dto);
	}
	
	public List<ProjectDTO> viewProjects() {
		List<ProjectDTO> list = new ArrayList<ProjectDTO>();
		dao.findAll().forEach(list::add);
		return list;
		
	}
	
	public void deleteRecordById(Integer id) {
		dao.deleteById(id);
	}
	
	public void update(ProjectDTO dto) {
		dao.save(dto);
	}
	
	public ProjectDTO  getProject(Integer id) {
		return dao.getOne(id);
	}
	@Transactional
	public void  updateProjectName(String projectname,Integer id) {
		dao.setProjectNameFor(projectname, id);
	}
	
	@Transactional
	public void  updateProjectType(String projectType,Integer id) {
		dao.setProjectTypeFor(projectType, id);
	}
	
	@Transactional
	public void  updateProjectCode(String projectCode,Integer id) {
		dao.setProjectCodeFor(projectCode, id);
	}
	
	@Transactional
	public void  updateProjectDesc(String projectDescription,Integer id) {
		dao.setProjectDescFor(projectDescription, id);
	}
	
	@Transactional
	public void  updateProjectETA(Date ETA,Integer id) {
		dao.setProjectETAFor(ETA, id);
	}
	
	
}

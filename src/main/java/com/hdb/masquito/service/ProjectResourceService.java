package com.hdb.masquito.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdb.masquito.model.ProjectDTO;
import com.hdb.masquito.model.ProjectResourceDTO;
import com.hdb.masquito.repository.ProjectResourceDAO;

@Service
public class ProjectResourceService {
	@Autowired
	ProjectResourceDAO dao;
	
	public void saveProjectResource(ProjectResourceDTO dto) {
		dao.save(dto);
	}
	
	public List<ProjectResourceDTO> viewProjectResource() {
		List<ProjectResourceDTO> list = new ArrayList<ProjectResourceDTO>();
		dao.findAll().forEach(list::add);
		return list;
		
	}
	
	public void deleteProjectResourceById(Integer id) {
		dao.deleteById(id);
	}
	
	public void update(ProjectResourceDTO dto) {
		dao.save(dto);
	}
	
	public ProjectResourceDTO  getProjectResources(Integer id) {
		return dao.getOne(id);
	}
	
	
}

package com.hdb.masquito.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hdb.masquito.model.ResourceDTO;
import com.hdb.masquito.repository.ResourceDAO;

@Service
public class ResourceService {
	@Autowired
	ResourceDAO dao;
	public void saveResource(ResourceDTO dto) {
		dao.save(dto);
	}
	
	
	public List<ResourceDTO> viewResources(){
		List<ResourceDTO> list = new ArrayList<ResourceDTO>();
		dao.findAll().forEach(list::add);
		return list;
	}
	public void deleteResource(String rid) {
		dao.deleteById(rid);
	}
	
	public void updateResource(ResourceDTO dto) {
		dao.save(dto);
	}
	
	
	public ResourceDTO getResource(String id) {
		return dao.getOne(id);
	}
	
	public ResourceDTO getEmpidByEmpName(String emp) {
	
		return dao.findByempName(emp);

	
	}
	
	
	
}

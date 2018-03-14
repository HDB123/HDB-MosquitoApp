package com.hdb.masquito.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdb.masquito.model.AdminDTO;
import com.hdb.masquito.repository.AdminDAO;

@Service
public class AdminService {
	@Autowired
	AdminDAO adminDAO;
	public void ChangePassword(AdminDTO adminDTO) {
		adminDAO.save(adminDTO);
		
	}
	
	public AdminDTO getAdmin() {
		return adminDAO.getOne(1);
	}
	
	
}

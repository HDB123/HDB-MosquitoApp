package com.hdb.masquito.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hdb.masquito.model.AdminDTO;
import com.hdb.masquito.service.AdminService;

@RestController
public class AdminSettingsController {
	@Autowired
	AdminService adminService;
	
	public AdminSettingsController() {
		
	}
	
	@PostMapping("/adminChange")
public ModelAndView changeAdmin(AdminDTO adminDTO) {
	adminService.ChangePassword(adminDTO);
	return new ModelAndView();
}
	
}

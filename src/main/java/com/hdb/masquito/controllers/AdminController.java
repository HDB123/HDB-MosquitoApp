package com.hdb.masquito.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hdb.masquito.model.AdminDTO;
import com.hdb.masquito.model.ProjectDTO;
import com.hdb.masquito.model.ResourceDTO;
import com.hdb.masquito.service.AdminService;

@RestController
@Scope("session")
@SessionAttributes("username")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/**/login")
	public ModelAndView login() {
		return new ModelAndView("redirect:welcome#login");
	}

	@PostMapping("/**/login")
	public ModelAndView loginControl(AdminDTO adminDTO) {

		if (adminDTO.getUsername().equals("hdb") && adminDTO.getPassword().equals("hdb"))
			return new ModelAndView("AdminControlPanel", "username", "welcome " + adminDTO.getUsername())
					.addObject("username", adminDTO.getUsername());

		return new ModelAndView("redirect:welcome#login", "msg", " username or password wrong").addObject("username",
				adminDTO.getUsername());

	}

	@GetMapping("/**/AdminControlPanel")
	public ModelAndView AdminControlPanel(AdminDTO adminDTO, HttpSession session) {
		if ((session.getAttribute("username")) == null) {
			return new ModelAndView("loginpage", "msg", "please login first");
		}
		return new ModelAndView("AdminControlPanel").addObject("username", adminDTO.getUsername());
	}

}

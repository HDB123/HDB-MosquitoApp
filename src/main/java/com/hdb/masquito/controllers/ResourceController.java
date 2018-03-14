package com.hdb.masquito.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hdb.mailService.EmpMailSender;
import com.hdb.masquito.model.AdminDTO;
import com.hdb.masquito.model.ResourceDTO;
import com.hdb.masquito.model.ResourceLoginDTO;
import com.hdb.masquito.repository.ResourceDAO;
import com.hdb.masquito.service.ResourceService;
import com.hdb.masquito.validator.PasswordGenerator;

@Controller
@Scope("session")
public class ResourceController {

	@Autowired
	ResourceService resourceService;
	@Autowired
	EmpMailSender empMailSender;

	private String Empid;

	@PostMapping("/**/saveResource")
	public ModelAndView saveResource(@ModelAttribute("hdb1") @Validated ResourceDTO dto, BindingResult result,
			HttpSession session, Model model) {
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return new ModelAndView("CreateResource", "msg", "Enter all Details");
		}
		System.out.println(dto.getPassword());

		if ((session.getAttribute("username")) == null) {
			return new ModelAndView("redirect:welcome#login", "msg", "please login first");
		}

		dto.setPassword(new PasswordGenerator().getRandomPassword());

		resourceService.saveResource(dto);

		ResourceDTO lr = resourceService.getEmpidByEmpName(dto.getEmpName());
		System.out.println("emp id is " + lr.getEmpid());
		empMailSender.sendMailtoEmp(dto.getEmpName(), lr.getEmpid(), dto.getEmail(), dto.getPassword());
		return new ModelAndView("CreateResource", "msg", "resource has been saved");

	}

	@GetMapping("/**/viewResources")
	public ModelAndView viewResource(HttpSession session) {
		if ((session.getAttribute("username")) == null) {
			return new ModelAndView("loginpage", "msg", "please login first");
		}

		return new ModelAndView("/ResourcePages/viewResource", "list", resourceService.viewResources());

	}

	@GetMapping("/**/deleteResource/{id}")
	public ModelAndView deleteResourceById(@PathVariable String id, HttpSession session) {
		if ((session.getAttribute("username")) == null) {
			return new ModelAndView("loginpage", "msg", "please login first");
		}
		resourceService.deleteResource(id);
		return new ModelAndView("/ResourcePages/viewResource", "msg", "delete success").addObject("list",
				resourceService.viewResources());
	}

	@PostMapping("/**/updateResource/{id}")
	public ModelAndView updateResource(ResourceDTO dto, HttpSession session) {
		if ((session.getAttribute("username")) == null) {
			return new ModelAndView("loginpage", "msg", "please login first");
		}
		resourceService.updateResource(dto);

		return new ModelAndView("/ResourcePages/viewResource", "msg", "update success").addObject("list",
				resourceService.viewResources());

	}

	@GetMapping("/getResource/{id}")
	public ModelAndView getResource(@PathVariable String id, HttpSession session) {
		if ((session.getAttribute("username")) == null) {
			return new ModelAndView("loginpage", "msg", "please login first");
		}
		return new ModelAndView("/ResourcePages/editResource", "dto", resourceService.getResource(id)).addObject("list",
				resourceService.viewResources());
	}

	@PostMapping("/**/rlogin")
	public ModelAndView ResourceloginControl(ResourceLoginDTO dto) {

		ResourceDTO resourceDTO = resourceService.getResource(dto.getEmpid());

		if (dto.getUsername().equals(resourceDTO.getEmail()) && dto.getPassword().equals(resourceDTO.getPassword()))
			return new ModelAndView("IssusesControlPanel", "username", "welcome " + resourceDTO.getEmpName())
					.addObject("username", resourceDTO.getEmpName());

		return new ModelAndView("redirect:welcome#login", "msg", " username or password wrong").addObject("username",
				resourceDTO.getEmpName());

	}

}

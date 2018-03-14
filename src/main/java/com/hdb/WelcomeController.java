package com.hdb;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import com.hdb.masquito.model.ProjectDTO;
import com.hdb.masquito.model.ResourceDTO;

@Controller
@SessionScope
@SessionAttributes("username")
public class WelcomeController {
	@GetMapping("/welcome")
	public String welcome() {

		return "welcome";
	}

	@GetMapping("/AdminLogin")
	public String AdminLogin() {
		return "AdminLogin";
	}

	@GetMapping("/CreateProject")
	public ModelAndView CreateProject(HttpSession session) {
		if ((session.getAttribute("username")) == null) {
			return new ModelAndView("redirect:welcome#login", "msg", "please login first");
		}
		return new ModelAndView("CreateProject");

	}

	@GetMapping("/CreateResource")
	public ModelAndView CreateResource(HttpSession session) {
		if ((session.getAttribute("username")) == null) {
			return new ModelAndView("redirect:welcome#login", "msg", "please login first");
		}
		return new ModelAndView("CreateResource");

	}
	
	
	@GetMapping("/ResourceLogin")
	public ModelAndView ResourceLogin(HttpSession session) {
		if ((session.getAttribute("username")) == null) {
			return new ModelAndView("redirect:welcome#login", "msg", "please login first");
		}
		return new ModelAndView("ResourceLogin");

	}


	@ModelAttribute("hdb")
	public ProjectDTO projectdto() {
		return new ProjectDTO();
	}

	@ModelAttribute("hdb1")
	public ResourceDTO resourceDTO() {
		return new ResourceDTO();

	}
}

package com.hdb.masquito.controllers;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hdb.masquito.model.ProjectDTO;
import com.hdb.masquito.model.ResourceDTO;
import com.hdb.masquito.service.ProjectService;
import com.hdb.masquito.service.ResourceService;
import com.hdb.masquito.validator.EditProjectValidator;

@Controller
@Scope("session")
@SessionAttributes("username")
public class projectController {
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ResourceService resourceService;

	@ModelAttribute("hdb1")
	public ResourceDTO resourceDTO() {
		return new ResourceDTO();
	}

	@PostMapping("/**/SaveProject")
	public ModelAndView projectSave(@ModelAttribute("hdb") @Validated ProjectDTO project, BindingResult result,
			Model model, HttpSession session) {
		if (result.hasErrors()) {
			System.out.println("error occured");
			return new ModelAndView("CreateProject");

		}

		if ((session.getAttribute("username")) == null) {
			return new ModelAndView("redirect:welcome#login", "msg", "please login first");
		}

		model.addAttribute("hdb", new ProjectDTO());

		System.out.println("saving-----------");
		System.out.println(project.getProjectName());

		projectService.saveProject(project);
		return new ModelAndView("createProject", "msg", "project has been saved");

	}

	@GetMapping("/**/ViewProject")
	public ModelAndView viewProjects(HttpSession session) {

		if ((session.getAttribute("username")) == null) {
			
			return new ModelAndView("redirect:welcome#login", "msg", "please login first");
		}
		return new ModelAndView("ViewProject").addObject("projects", projectService.viewProjects());

	}

	@GetMapping("/**/deleteProject/{id}")
	public ModelAndView deleteProject(@PathVariable Integer id, HttpSession session) {
		if ((session.getAttribute("username")) == null) {
			return new ModelAndView("loginpage", "msg", "please login first");
		}
		projectService.deleteRecordById(id);
		return new ModelAndView("viewProjects", "msg", "project deleted succesfully").addObject("list",
				projectService.viewProjects());

	}

	@PostMapping("/**/updateProject/{id}")
	public ModelAndView updateProject(@PathVariable Integer id, ProjectDTO dto, HttpSession session) {
		if ((session.getAttribute("username")) == null) {
			return new ModelAndView("loginpage", "msg", "please login first");
		}

		projectService.update(dto);
		return new ModelAndView("viewProjects", "msg", "project updated succesfully").addObject("list",
				projectService.viewProjects());
	}

	@GetMapping("/**/getProject/{id}")
	public ModelAndView getProject(@PathVariable Integer id, HttpSession session) {
		if ((session.getAttribute("username")) == null) {
			return new ModelAndView("loginpage", "msg", "please login first");
		}
		System.out.println(projectService.getProject(id).getProjectDescription());
		return new ModelAndView("editProject", "dto", projectService.getProject(id));
	}

	@GetMapping("/**/createResource")
	public ModelAndView createResource(HttpSession session) {
		if ((session.getAttribute("username")) == null) {
			return new ModelAndView("loginpage", "msg", "please login first");
		}
		return new ModelAndView("/ResourcePages/createResource", "list", projectService.viewProjects());
	}

	@GetMapping("/**/assignProject")
	public ModelAndView assignProject(HttpSession session) {
		if ((session.getAttribute("username")) == null) {
			return new ModelAndView("loginpage", "msg", "please login first");
		}

		System.out.println("res" + resourceService.viewResources().get(0));
		return new ModelAndView("/ProjectResource/AssignProjectResources", "rList", resourceService.viewResources())
				.addObject("pList", projectService.viewProjects());

	}

	@GetMapping("/**/createProject")
	public ModelAndView createProject(HttpSession session,RedirectAttributes ra) {
		if ((session.getAttribute("username")) == null) {
			ra.addFlashAttribute("msg", "pls login first");
			return new ModelAndView("redirect:welcome#login", "msg", "please login first");
		}
		return new ModelAndView("createProject");
	}

	@PostMapping("/**/updateProjectData")
	public ModelAndView updateProjectName( @RequestParam(value="value") String projName,@RequestParam(value="pk") int pk,@RequestParam(value="name") String name,
			HttpSession session, RedirectAttributes ra) {
		
		System.out.println("pkkkk " +name);
		if ((session.getAttribute("username")) == null) {
			ra.addFlashAttribute("msg", "pls login first");
			return new ModelAndView("redirect:welcome#login", "msg", "please login first");
		}
		ra.addFlashAttribute("hdb", new ProjectDTO());
		 projectService.updateProjectName(projName, pk);
		ra.addFlashAttribute("msg", "project name updated successfully");
		return new ModelAndView("redirect:createProject#viewProjectModel");

	}

}

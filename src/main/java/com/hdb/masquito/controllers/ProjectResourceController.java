package com.hdb.masquito.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hdb.masquito.model.ProjectDTO;
import com.hdb.masquito.model.ProjectResourceDTO;
import com.hdb.masquito.service.ProjectResourceService;
import com.hdb.masquito.service.ProjectService;
import com.hdb.masquito.service.ResourceService;

@RestController
@Scope("session")
public class ProjectResourceController {

	@Autowired
	private ProjectResourceService projectResourceService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ResourceService resourceService;
	@PostMapping("/projectResourcesSave")
	public ModelAndView projectResourcesSave(ProjectResourceDTO dto, HttpSession session) {

		if ((session.getAttribute("username")) == null) {
			return new ModelAndView("loginpage", "msg", "please login first");
		}

		projectResourceService.saveProjectResource(dto);
		return new ModelAndView("ProjectResource/AssignProjectResources","msg","Project Assigned")
				
				.addObject("pList",projectService.viewProjects())
				.addObject("rList",resourceService.viewResources())
				;

	}

	@GetMapping("/viewProjectResource")
	public ModelAndView viewProjectResource(HttpSession session) {

		if ((session.getAttribute("username")) == null) {
			return new ModelAndView("loginpage", "msg", "please login first");
		}

		return new ModelAndView("ProjectResource/viewProjectResources", "list", projectResourceService.viewProjectResource());

	}

	@GetMapping("/deleteProjectResources/{id}")
	public ModelAndView deleteProjectResources(@PathVariable Integer id, HttpSession session) {
		if ((session.getAttribute("username")) == null) {
			return new ModelAndView("loginpage", "msg", "please login first");
		}
		projectResourceService.deleteProjectResourceById(id);
		return new ModelAndView("viewProjects", "msg", "project deleted succesfully").addObject("list",
				projectResourceService.viewProjectResource());

	}

	@PostMapping("/getProject/updateProjectResources/{id}")
	public ModelAndView updateProjectResources(@PathVariable Integer id, ProjectResourceDTO dto, HttpSession session) {
		if ((session.getAttribute("username")) == null) {
			return new ModelAndView("loginpage", "msg", "please login first");
		}

		projectResourceService.update(dto);
		return new ModelAndView("viewProjects", "msg", "project updated succesfully").addObject("list",
				projectResourceService.viewProjectResource());
	}

	@GetMapping("/getProjectResources/{id}")
	public ModelAndView getProjectResources(@PathVariable Integer id, HttpSession session) {
		if ((session.getAttribute("username")) == null) {
			return new ModelAndView("loginpage", "msg", "please login first");
		}
		return new ModelAndView("editProject", "dto", projectResourceService.getProjectResources(id));
	}

}

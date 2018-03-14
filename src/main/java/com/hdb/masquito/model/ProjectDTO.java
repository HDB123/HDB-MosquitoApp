package com.hdb.masquito.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ProjectTable")
public class ProjectDTO implements Serializable {
	@Id
	@GenericGenerator(name = "seq", strategy = "increment")
	@GeneratedValue(generator = "seq")
	private int id;
	@Size(min = 3, max = 50, message = "The Project Name '${validatedValue}' must be between {min} and {max} characters long")
	@NotEmpty(message = "Enter Valid Name")
	private String projectName;

	private String projectType;
	@NotEmpty(message = "Enter Valid Code")
	@Size(min = 3, max = 10)
	private String projectCode;

	@Temporal(TemporalType.DATE)
	@NotNull(message = "Enter Valid Date")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date startDate;
	@NotNull(message = "Enter Valid Description")
	@Size(min = 5, max = 200)
	private String projectDescription;
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Enter Valid ETA")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	@Future(message = "please Enter a future Date")
	private Date ETA;
	@NotNull(message = "Enter Valid status")
	private boolean projectStatus;

	public ProjectDTO() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass().getName());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public Date getProjectDate() {
		return startDate;
	}

	public void setProjectDate(Date projectDate) {
		this.startDate = projectDate;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public Date getETA() {
		return ETA;
	}

	public void setETA(Date eTA) {
		ETA = eTA;
	}

	public boolean isProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(boolean projectStatus) {
		this.projectStatus = projectStatus;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}

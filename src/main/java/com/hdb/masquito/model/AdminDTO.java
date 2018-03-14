package com.hdb.masquito.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table
public class AdminDTO {
	@Id
	private Integer id;
	private String username;
	private String password;
	
	public AdminDTO() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass().getName());
		
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}

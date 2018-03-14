package com.hdb.masquito.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ProjectResourceTable")
public class ProjectResourceDTO {
	@Id
	@GenericGenerator(name = "seq", strategy = "increment")
	@GeneratedValue(generator = "seq")
	private int aid;
	
	private int rid;
	@ElementCollection
	private List<Integer> pid;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public List<Integer> getPid() {
		return pid;
	}
	public void setPid(List<Integer> pid) {
		this.pid = pid;
	}
	
	

}

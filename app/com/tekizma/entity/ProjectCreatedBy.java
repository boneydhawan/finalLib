package com.tekizma.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "project_created_by")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "ProjectCreatedBy.findAll", query = "SELECT b FROM ProjectCreatedBy b"),
		@NamedQuery(name = "ProjectCreatedBy.findById", query = "SELECT b FROM ProjectCreatedBy b WHERE b.id = :id") })

public class ProjectCreatedBy {
	
	@Id
    @SequenceGenerator(name="project_created_by_seq", sequenceName="project_created_by_seq")
    @GeneratedValue(strategy=GenerationType.AUTO,generator="project_created_by_seq")
	private Long id;

	 @Column(name = "name")
    private String name;
     
    @Column(name = "branch")
    private String branch;
    
    @Column(name = "rollNo")
    private String rollNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
    
    
}

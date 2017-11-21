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
@Table(name = "about_project")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "AboutProject.findAll", query = "SELECT b FROM AboutProject b"),
		@NamedQuery(name = "AboutProject.findById", query = "SELECT b FROM AboutProject b WHERE b.id = :id") })
public class AboutProject {
	
	@Id
    @SequenceGenerator(name="about_project_seq", sequenceName="about_project_seq")
    @GeneratedValue(strategy=GenerationType.AUTO,generator="about_project_seq")
	private Long id;

	@Column(name = "project_title")
    private String projectTitle;
     
    @Column(name = "project_details")
    private String projectDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getProjectDetails() {
		return projectDetails;
	}

	public void setProjectDetails(String projectDetails) {
		this.projectDetails = projectDetails;
	}
        
}

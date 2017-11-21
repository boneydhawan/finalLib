package com.tekizma.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import com.tekizma.coreServices.Persistent;

@Entity
@Table(name = "role")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r"),
		@NamedQuery(name = "Role.findById", query = "SELECT r FROM Role r WHERE r.id = :id"),
		@NamedQuery(name = "Role.findByRole", query = "SELECT r FROM Role r WHERE r.role = :role") })
public class Role {
	
	@Id
    @SequenceGenerator(name="role_seq", sequenceName="role_seq")
    @GeneratedValue(strategy=GenerationType.AUTO,generator="role_seq")
	private Long id;
	
	@Column(name = "role")
    private String role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}

package com.tekizma.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.tekizma.coreServices.Persistent;
import com.tekizma.entity.Role;
/**
*
* @author boney dhawan
*/

@Entity
@Table(name = "user_profile")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "UserProfile.findAll", query = "SELECT u FROM UserProfile u"),
		@NamedQuery(name = "UserProfile.findById", query = "SELECT u FROM UserProfile u WHERE u.id = :id") })
public class UserProfile{
	
	private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="user_profile_seq", sequenceName="user_profile_seq")
    @GeneratedValue(strategy=GenerationType.AUTO,generator="user_profile_seq")
	private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "roll_no")
    private String rollNo;
    
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	/*@Column(name = "role_id")
    private Long roleId;*/
    
	@ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	/*public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}*/

	/*public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}*/

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
     	
}

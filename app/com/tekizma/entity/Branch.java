package com.tekizma.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.tekizma.coreServices.Persistent;

@Entity
@Table(name = "branch")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Branch.findAll", query = "SELECT b FROM Branch b"),
		@NamedQuery(name = "Branch.findById", query = "SELECT b FROM Branch b WHERE b.id = :id") })
public class Branch {
	
	@Id
    @SequenceGenerator(name="branch_seq", sequenceName="branch_seq")
    @GeneratedValue(strategy=GenerationType.AUTO,generator="branch_seq")
	private Long id;
	
	@Column(name = "branch_name")
    private String branchName;
	
	@Column(name = "branch_detail")
    private String branchDetail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchDetail() {
		return branchDetail;
	}

	public void setBranchDetail(String branchDetail) {
		this.branchDetail = branchDetail;
	}
	
	

}

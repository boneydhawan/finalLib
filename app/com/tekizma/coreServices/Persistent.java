package com.tekizma.coreServices;

import java.util.Date;
import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class Persistent implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "updated_by")
	private String updatedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@PrePersist
	public void onCreate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		this.setCreatedDate(calendar.getTime());
		this.setUpdatedDate(calendar.getTime());
		this.setCreatedBy("SYSTEM");
		this.setUpdatedBy("SYSTEM");
	}
	
	@PreUpdate
	public void onUpdate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		this.setUpdatedDate(calendar.getTime());
		this.setUpdatedBy("SYSTEM");
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String creadtedBy) {
		this.createdBy = creadtedBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}

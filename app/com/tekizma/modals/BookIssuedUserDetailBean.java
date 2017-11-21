package com.tekizma.modals;

import java.util.Date;

import com.tekizma.entity.UserProfile;

public class BookIssuedUserDetailBean {
	private long id;
	private long fine;
	private UserProfile userDetails;
	private Date lastReturnDate;
	private Date issuedDate;
	public long getFine() {
		return fine;
	}
	public void setFine(long diff) {
		this.fine = diff;
	}
	public UserProfile getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserProfile userDetails) {
		this.userDetails = userDetails;
	}
	public Date getLastReturnDate() {
		return lastReturnDate;
	}
	public void setLastReturnDate(Date lastReturnDate) {
		this.lastReturnDate = lastReturnDate;
	}
	public Date getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}

package com.tekizma.entity;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "book_issued")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "BookIssued.findAll", query = "SELECT b FROM BookIssued b"),
		@NamedQuery(name = "BookIssued.findById", query = "SELECT b FROM BookIssued b WHERE b.id = :id") })
public class BookIssued {
	@Id
    @SequenceGenerator(name="book_issued_seq", sequenceName="book_issued_seq")
    @GeneratedValue(strategy=GenerationType.AUTO,generator="book_issued_seq")
	private Long id;

	
	@ManyToOne
    @JoinColumn(name = "book_id")
	private BookDetail bookDetail;
    
	@ManyToOne
	@JoinColumn(name = "user_profile_id")
	private UserProfile userProfile;
     
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "issued_date")
    private Date issuedDate;
    
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "supposed_return_date")
    private Date supposedReturnDate;
    
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "actual_return_date")
    private Date actualReturnDate;
	
	@Column(name = "fine")
    private int fine;
	
	@Column(name = "is_returned")
    private Long isReturned;
	
	public BookDetail getBookDetail() {
		return bookDetail;
	}

	public void setBookDetail(BookDetail bookDetail) {
		this.bookDetail = bookDetail;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public Date getSupposedReturnDate() {
		return supposedReturnDate;
	}

	public void setSupposedReturnDate(Date supposedReturnDate) {
		this.supposedReturnDate = supposedReturnDate;
	}

	public Date getActualReturnDate() {
		return actualReturnDate;
	}

	public void setActualReturnDate(Date actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getFine() {
		return fine;
	}

	public void setFine(int fine) {
		this.fine = fine;
	}

	public Long getIsReturned() {
		return isReturned;
	}

	public void setIsReturned(Long isReturned) {
		this.isReturned = isReturned;
	}

	
}

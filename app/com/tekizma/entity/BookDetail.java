package com.tekizma.entity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.*;
import com.tekizma.coreServices.Persistent;

@Entity
@Table(name = "books_detail")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "BookDetail.findAll", query = "SELECT b FROM BookDetail b"),
		@NamedQuery(name = "BookDetail.findById", query = "SELECT b FROM BookDetail b WHERE b.id = :id") })
public class BookDetail {
	
	@Id
    @SequenceGenerator(name="book_detail_seq", sequenceName="book_detail_seq")
    @GeneratedValue(strategy=GenerationType.AUTO,generator="book_detail_seq")
	private Long id;

	@Column(name = "book_name")
    private String bookName;
	
	@Column(name = "author")
    private String author;
	
	@Column(name = "book_detail")
    private String bookDetail;
	
	@ManyToOne
    @JoinColumn(name = "book_category_id")
    private BookCategory bookCategory;

	@Column(name = "quantity")
    private int quantity;
	
	@Column(name = "is_Delete")
    private Long isDelete;
	


	public Long getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Long isDelete) {
		this.isDelete = isDelete;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBookDetail() {
		return bookDetail;
	}

	public void setBookDetail(String bookDetail) {
		this.bookDetail = bookDetail;
	}


	public BookCategory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
		
	
}

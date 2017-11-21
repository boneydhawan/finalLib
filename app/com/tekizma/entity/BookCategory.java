package com.tekizma.entity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.*;
import com.tekizma.coreServices.Persistent;

@Entity
@Table(name = "book_category")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "BookCategory.findAll", query = "SELECT b FROM BookCategory b"),
		@NamedQuery(name = "BookCategory.findById", query = "SELECT b FROM BookCategory b WHERE b.id = :id") })
public class BookCategory {
	
	@Id
    @SequenceGenerator(name="book_category_seq", sequenceName="book_category_seq")
    @GeneratedValue(strategy=GenerationType.AUTO,generator="book_category_seq")
	private Long id;

	 @Column(name = "book_category")
    private String bookCategory;
     
    @Column(name = "book_category_detail")
    private String bookCategoryDetail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}

	public String getBookCategoryDetail() {
		return bookCategoryDetail;
	}

	public void setBookCategoryDetail(String bookCategoryDetail) {
		this.bookCategoryDetail = bookCategoryDetail;
	}
 
}

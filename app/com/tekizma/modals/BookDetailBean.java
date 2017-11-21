package com.tekizma.modals;

import com.tekizma.entity.BookCategory;

public class BookDetailBean {
	private Long id;
	private String bookName;
	private String authorName;
	private String bookDetail;
	private int quantity;
	private BookCategory bookCategory;
	private int booksQuantityLeft;
	
	public int getBooksQuantityLeft() {
		return booksQuantityLeft;
	}
	public void setBooksQuantityLeft(int booksQuantityLeft) {
		this.booksQuantityLeft = booksQuantityLeft;
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
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getBookDetail() {
		return bookDetail;
	}
	public void setBookDetail(String bookDetail) {
		this.bookDetail = bookDetail;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BookCategory getBookCategory() {
		return bookCategory;
	}
	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}
	
}

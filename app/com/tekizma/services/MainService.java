package com.tekizma.services;

import java.util.HashMap;
import java.util.List;

import com.tekizma.entity.BookCategory;
import com.tekizma.entity.BookIssued;
import com.tekizma.entity.Role;
import com.tekizma.entity.UserProfile;
import com.tekizma.modals.BookDetailBean;
import com.tekizma.modals.BookIssuedUserDetailBean;
import com.tekizma.modals.UserProfileBean;


/**
*
* @author boney dhawan
*/
public interface MainService {
    
    public Boolean addStudent(String name,String email, String password, String phone,String rollNo,String branchId,String roleId);
    public UserProfileBean validateUser(String email, String password);
    public List<BookDetailBean> getBookDetails();
    public List<BookCategory> getBookCategory();
    public List<Role> getRoles();
    public Boolean removeBook(String bookId);
    
    public List<UserProfile> getAllUserProfile();
    public Boolean addBook(String bookName,String author,String bookDetail,String quantity,String bookCategoryId);
    public HashMap<String,List> getProjectDetails();
    public Boolean changePassword(String userId,String oldPassword,String newPassword);
    public UserProfileBean getUserBasedOnRollNo(String rollNo);
    
    public Boolean issueBook(String userId,String bookId);
    public Boolean changeQuantity(String quantity,String bookId);
    
    public List<BookIssuedUserDetailBean> getBookIssueUsers(String bookId);
    public Boolean returnedBook(String bookIssuedId,String fine);
    public List<BookIssued> userCurrentIssuedBookHistory(String userId);
    public List<BookIssued> userOldIssuedBookHistory(String userId);
    
    public Boolean updateDetails(String userId,String email,String phone);
    public String sendEmail(String email);
    
/*    public String validateUser(String email, String password);
    public String delContact(String chatId);*/
}

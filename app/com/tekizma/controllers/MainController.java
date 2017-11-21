package com.tekizma.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;

import com.tekizma.controllers.routes;
import com.tekizma.coreUtils.CommonUtils;
import com.tekizma.modals.UserProfileBean;
import com.tekizma.modals.LoginResult;
import com.tekizma.modals.BookDetailBean;
import com.tekizma.modals.BookIssuedUserDetailBean;
import com.tekizma.services.MainService;
import com.tekizma.entity.BookCategory;
import com.tekizma.entity.UserProfile;
import com.tekizma.entity.Role;
import com.tekizma.entity.BookIssued;

//import jdk.nashorn.internal.ir.ObjectNode;
import play.db.jpa.Transactional;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.mvc.*;
import views.html.*;
import static play.libs.Json.toJson;


/**
*
* @author boney dhawan
*/
public class MainController extends Controller {


	private final FormFactory formFactory;
    private final JPAApi jpaApi;
    private final MainService mainService;
    private final String TRUE_TEXT="true";
    private final String FALSE_TEXT="false";
    

    
    @Inject
    public MainController(FormFactory formFactory, JPAApi jpaApi,MainService mainService) {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
        this.mainService = mainService;
    }
    
    @Transactional
    public Result addStudent() {
        String name = request().getQueryString("name");
        String email = request().getQueryString("email");
        String password = request().getQueryString("password");
        String phone = request().getQueryString("phone");
        String rollNo = request().getQueryString("rollNo");
        String branchId = request().getQueryString("branchId");
        String roleId = request().getQueryString("roleId");
        Boolean status=mainService.addStudent(name,email,password,phone,rollNo,branchId,roleId);
        if(status == true){
        	List<UserProfile> userProfileDetails= mainService.getAllUserProfile();
        	return ok(toJson(userProfileDetails));
        }
        else
        	return status(409,"Student already exist");
    }
    
    @Transactional
    public Result removeBook() {
        String bookId = request().getQueryString("bookId");
        Boolean status=mainService.removeBook(bookId);
        if(status == true){
        	List<BookDetailBean> booksDetails= mainService.getBookDetails();
        	System.out.println("No of Books Fetched: "+booksDetails.size());
        	return ok(toJson(booksDetails));
        }
        else
        	return status(409,"Error in Deleting book");
    }
    
    @Transactional
    public Result changePassword() {
    	
    	String oldPassword = request().getQueryString("oldPassword");
        String newPassword = request().getQueryString("newPassword");
        String userId = request().getQueryString("userId");
        Boolean status=mainService.changePassword(userId,oldPassword,newPassword);
        if(status == true){
        	return ok(TRUE_TEXT);
        }
        else
        	return status(409,"Old Password doesnot match");
    }
    
    @Transactional
    public Result getUserBasedOnRollNo() {
    	
    	String rollNo = request().getQueryString("rollNo");
    	UserProfileBean userDetail=mainService.getUserBasedOnRollNo(rollNo);
        if(userDetail.getName() != null || userDetail.getRoleBean() != null){
        	return ok(toJson(userDetail));
        }
        else
        	return status(404,"Roll number does not match.");
    }
    
    
    @Transactional
    public Result isAuthorized() {
        String email = request().getQueryString("email");
        String password = request().getQueryString("password");
        System.out.println("Login User Email :" +email +" & password = "+password);
        UserProfileBean  userProfileBean = (UserProfileBean) mainService.validateUser(email,password);
   
        if(userProfileBean.getName() != null  || userProfileBean.getRoleBean() != null)
        	return ok(toJson(userProfileBean));
        else
        	return status(401,"User not authorized.");
    }
    
    @Transactional
    public Result issueBook() {
        String userId = request().getQueryString("userId");
        String bookId = request().getQueryString("bookId");
        System.out.println("Book Issued for userId :" +userId +" & bookId = "+bookId);
        Boolean status= mainService.issueBook(userId,bookId);
        if(status == true){
        	List<BookDetailBean> booksDetails= mainService.getBookDetails();
        	return ok(toJson(booksDetails));
        }
        else
        	return status(401,"Error while issuing book.");
    }
    
    @Transactional
    public Result changeQuantity() {
        String quantity = request().getQueryString("quantity");
        String bookId = request().getQueryString("bookId");
        System.out.println("Book Id :" +bookId +" & quantity = "+quantity);
        Boolean status= mainService.changeQuantity(quantity,bookId);
        if(status == true){
        	List<BookDetailBean> booksDetails= mainService.getBookDetails();
        	return ok(toJson(booksDetails));
        }
        else
        	return status(401,"Error while Saving Book Details.");
    }
    
    @Transactional
    public Result getBooksDetail() {
        List<BookDetailBean> booksDetails= mainService.getBookDetails();
        System.out.println("No of Books Fetched: "+booksDetails.size());
        return ok(toJson(booksDetails));
    }
    
    @Transactional
    public Result getBooksCategory() {
        List<BookCategory> booksCategory= mainService.getBookCategory();
        return ok(toJson(booksCategory));
    }
    
    @Transactional
    public Result getRoles() {
        List<Role> roles= mainService.getRoles();
        return ok(toJson(roles));
    }
    
    @Transactional
    public Result getAllUserDetails() {
        List<UserProfile> userProfileDetails= mainService.getAllUserProfile();
        return ok(toJson(userProfileDetails));
    }
    
    @Transactional
    public Result getProjectDetails() {
    	HashMap<String,List>  projectDetails= mainService.getProjectDetails();
        return ok(toJson(projectDetails));
    }
    
    @Transactional
    public Result addBookToDb() {
    	String bookName = request().getQueryString("bookName");
        String author = request().getQueryString("author");
        String bookDetail = request().getQueryString("bookDetail");
        String quantity = request().getQueryString("quantity");
        String bookCategoryId = request().getQueryString("bookCategoryId");
        Boolean status=mainService.addBook(bookName,author,bookDetail,quantity,bookCategoryId);
        if(status == true){
        	List<BookDetailBean> booksDetails= mainService.getBookDetails();
            System.out.println("No of Books Fetched: "+booksDetails.size());
            return ok(toJson(booksDetails));
        }
        else
        	return status(409,"Error in Adding book");
    }
    
    @Transactional
    public Result getBookIssueUsers() {
    	String bookId = request().getQueryString("bookId");
    	List<BookIssuedUserDetailBean> bookIssuedUserDetailBean= mainService.getBookIssueUsers(bookId);
        return ok(toJson(bookIssuedUserDetailBean));
    }
    
    @Transactional
    public Result returnedBook() {
    	String bookId = request().getQueryString("bookId");
    	String bookIssuedId = request().getQueryString("bookIssuedId");
    	String fine = request().getQueryString("fine");
    	Boolean status=mainService.returnedBook(bookIssuedId,fine);
    	if(status == true){
    		List<BookIssuedUserDetailBean> bookIssuedUserDetailBean= mainService.getBookIssueUsers(bookId);
    		return ok(toJson(bookIssuedUserDetailBean));
    	}else{
    		return status(500,"Error while returning book");
    	}
        
    }
    
    @Transactional
    public Result getBookHistory() {
    	String userId = request().getQueryString("userId");
    	List<BookIssued> listCurrentBookIssued= mainService.userCurrentIssuedBookHistory(userId);
    	List<BookIssued> listOldBookIssued= mainService.userOldIssuedBookHistory(userId);
    	HashMap<String,List> mapOfBookIssued = new HashMap<String,List>();
    	mapOfBookIssued.put("listCurrentBookIssued",listCurrentBookIssued);
    	mapOfBookIssued.put("listOldBookIssued",listOldBookIssued);
    	return ok(toJson(mapOfBookIssued));
    }
    
    @Transactional
    public Result updateDetails() {
    	String userId = request().getQueryString("userId");
        String email = request().getQueryString("email");
        String phone = request().getQueryString("phone");
        Boolean status=mainService.updateDetails(userId,email,phone);
        if(status == true){
        	return ok(TRUE_TEXT);
        }
        else
        	return status(409,"Error while saving detail");
    }
    
    @Transactional
    public Result forgotPassword() {
    	String email = request().getQueryString("email");
    	String status=mainService.sendEmail(email);
    	if(status == "sent"){
    		return ok(TRUE_TEXT);
    	}
    	else if(status == "notFound"){
    		return status(409,"Email not Found");
    	}else{
    		return status(409,"Error while sending email.");
    	}
    }
    
}


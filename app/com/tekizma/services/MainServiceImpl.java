package com.tekizma.services;

import javax.inject.Inject;

import com.tekizma.coreServices.CommonDao;
import com.tekizma.coreUtils.CommonUtils;
import com.tekizma.entity.AboutProject;
import com.tekizma.entity.BookCategory;
import com.tekizma.entity.BookDetail;
import com.tekizma.entity.BookIssued;
import com.tekizma.entity.Branch;
import com.tekizma.entity.ProjectCreatedBy;
import com.tekizma.entity.Role;
import com.tekizma.entity.UserProfile;
import com.tekizma.modals.BookDetailBean;
import com.tekizma.modals.BookIssuedUserDetailBean;
import com.tekizma.modals.UserProfileBean;

import play.db.jpa.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.Properties;    
import javax.mail.*;    
import javax.mail.internet.*; 


import play.db.jpa.JPAApi;
import play.api.Logger;
/**
*
* @author boney dhawan
*/


public class MainServiceImpl implements MainService{
	
    private CommonDao commonDao;
    

    @Inject
    public MainServiceImpl(JPAApi jpaApi, CommonDao commonDao){
        this.commonDao = commonDao;
    }
   
    public Boolean addStudent(String name,String email, String password, String phone,String rollNo,String branchId, String roleId){
    	if(!(CommonUtils.isEmpty(name) || CommonUtils.isEmpty(email) || CommonUtils.isEmpty(password) || CommonUtils.isEmpty(phone)  || CommonUtils.isEmpty(rollNo) || CommonUtils.isEmpty(roleId))){
    	    UserProfile userProfile= new UserProfile();
    	    try{
	    	    userProfile.setName(name);
		        userProfile.setEmail(email);
		        userProfile.setPassword(password);
		        userProfile.setPhone(phone);
		        userProfile.setRollNo(rollNo);
		        String q="select r from Role r where id="+roleId; 
	    		List<Role> roleInfo =  (List<Role>) commonDao.executeNativeQuery(q);
	    		
	    		String query="select r from Branch r where id="+branchId; 
	    		List<Branch> branchInfo =  (List<Branch>) commonDao.executeNativeQuery(query);
		        userProfile.setRole(roleInfo.get(0));
		        userProfile.setBranch(branchInfo.get(0));
	    
	        	commonDao.create(userProfile);
			    return true;
	        }
	        catch(Exception e){
	        	System.out.println(e);
			    return false;
	        }	    	   
    	}
    	else{
    		 return false;
    	}
    }
    
    public UserProfileBean validateUser(String email, String password){
    	UserProfileBean userInfoBean = new UserProfileBean();
    	if(!(CommonUtils.isEmpty(email) || CommonUtils.isEmpty(password)) ){
    		String q="select p from UserProfile p where email='"+email+"' and password='"+password+"'";
    		List<UserProfile> userInfo =  (List<UserProfile>) commonDao.executeNativeQuery(q);
    		if(!userInfo.isEmpty()){
    			userInfoBean.setId(userInfo.get(0).getId());
    			userInfoBean.setName(userInfo.get(0).getName());
    			userInfoBean.setEmail(userInfo.get(0).getEmail());
    			userInfoBean.setPhone(userInfo.get(0).getPhone());
    			userInfoBean.setRollNo(userInfo.get(0).getRollNo());
    			userInfoBean.setRoleBean(userInfo.get(0).getRole());
    			userInfoBean.setBranch(userInfo.get(0).getBranch());
	        }
		 }
    	return userInfoBean;
    }
    
    public List<BookDetailBean> getBookDetails(){
    	String q="SELECT b FROM BookDetail b where isDelete=0";
    	List<BookDetailBean> bookDetailBeanList = new ArrayList<BookDetailBean>();
		List<BookDetail> bookDetail =  (List<BookDetail>) commonDao.executeNativeQuery(q);
		String query="select p from BookIssued p";
		List<BookIssued> bookIssuedList =  (List<BookIssued>) commonDao.executeNativeQuery(query);
		
		for(BookDetail books : bookDetail){
			BookDetailBean bookDetailList = new BookDetailBean();
			int bookCount=0;
			bookDetailList.setId(books.getId());
			bookDetailList.setQuantity(books.getQuantity());
			bookDetailList.setBookName(books.getBookName());
			bookDetailList.setAuthorName(books.getAuthor());
			bookDetailList.setBookCategory(books.getBookCategory());
			bookDetailList.setBookDetail(books.getBookDetail());
			
			for(BookIssued bookIssued : bookIssuedList){
				if(bookIssued.getBookDetail().getId() == books.getId()){
					bookCount++;
				}
			}
			int finalBookQuantityLeft=(books.getQuantity() - bookCount);
			bookDetailList.setBooksQuantityLeft(finalBookQuantityLeft);
			bookDetailBeanList.add(bookDetailList);
			
		}
    	return bookDetailBeanList;
    }
    
    public List<BookCategory> getBookCategory(){
    	String q="SELECT b FROM BookCategory b";
    	List<BookCategory> bookCategoryDetail =  (List<BookCategory>) commonDao.executeNativeQuery(q);
    	return bookCategoryDetail;
    }
    
    public List<UserProfile> getAllUserProfile(){
    	String q="SELECT u FROM UserProfile u";
    	List<UserProfile> userProfileDetails =  (List<UserProfile>) commonDao.executeNativeQuery(q);
    	return userProfileDetails;
    }
    
    
    public Boolean addBook(String bookName,String author,String bookDetail,String quantity,String bookCategoryId){
    	System.out.println("Book Name:"+bookName +" &author"+author+" bookDetail="+bookDetail+" quantity:"+quantity+" bookCategoryId"+bookCategoryId);
    	if(!(CommonUtils.isEmpty(bookName) || CommonUtils.isEmpty(author) || CommonUtils.isEmpty(bookDetail) || CommonUtils.isEmpty(quantity)  || CommonUtils.isEmpty(bookCategoryId))){
    		BookDetail bookDetails = new BookDetail();
    		bookDetails.setBookName(bookName);
    		bookDetails.setAuthor(author);
    		bookDetails.setBookDetail(bookDetail);
    		bookDetails.setQuantity(Integer.parseInt(quantity));
    		BookCategory bookCategory =  (BookCategory) commonDao.find(BookCategory.class, Long.parseLong(bookCategoryId));
    		bookDetails.setBookCategory(bookCategory);
    		bookDetails.setIsDelete(0L);
	        try{
	        	commonDao.create(bookDetails);
			    return true;
	        }
	        catch(Exception e){
	        	System.out.println(e);
			    return false;
	        }	    	   
    	}
    	else{
    		 return false;
    	}
    }
    
    public Boolean removeBook(String bookId){
    	System.out.println("Book Id delete id:"+ bookId);
    	try{
    		BookDetail bookDetail = (BookDetail) commonDao.find(BookDetail.class, Long.parseLong(bookId));
    		bookDetail.setIsDelete(1L);
    		commonDao.update(bookDetail);
    		return true;
    	}
    	catch(Exception e){
    		System.out.println(e);
    		return false;
    	}
    	
    }
    
    public List<Role> getRoles(){
    	String q="SELECT r FROM Role r";
    	List<Role> roles =  (List<Role>) commonDao.executeNativeQuery(q);
    	return roles;
    }
     
    public HashMap<String,List> getProjectDetails(){
    	HashMap<String,List> projectDetails = new HashMap<String,List>();
    	
    	String q="SELECT a FROM AboutProject a where id='1'";
    	List<AboutProject> aboutProjectList =  (List<AboutProject>) commonDao.executeNativeQuery(q);
    	
    	String query="SELECT p FROM ProjectCreatedBy p";
    	List<ProjectCreatedBy> projectCreatedByList =  (List<ProjectCreatedBy>) commonDao.executeNativeQuery(query);
    	
    	projectDetails.put("projectCreatedBy", projectCreatedByList);
    	projectDetails.put("aboutProject", aboutProjectList);
    	return projectDetails;
    } 
    
    public Boolean changePassword(String userId,String oldPassword,String newPassword){
    	System.out.println("Request Change password for Id:"+userId );
    	Boolean status=false;
    	try{
    		UserProfile userProfile = (UserProfile) commonDao.find(UserProfile.class, Long.parseLong(userId));
    		if(userProfile.getPassword().equals(oldPassword)){
    			userProfile.setPassword(newPassword);
    			commonDao.update(userProfile);
    			status=true;
    		}else{
    			status=false;
    		}
    	}
    	catch(Exception e){
    		System.out.println(e);
    	}
    	return status;  	
    }
    
    public UserProfileBean getUserBasedOnRollNo(String rollNo){
    	UserProfileBean userInfoBean = new UserProfileBean();
    	if(!(CommonUtils.isEmpty(rollNo))){
    		String q="select p from UserProfile p where rollNo='"+rollNo+"'";
    		List<UserProfile> userInfo =  (List<UserProfile>) commonDao.executeNativeQuery(q);
    		if(!userInfo.isEmpty()){
    			userInfoBean.setId(userInfo.get(0).getId());
    			userInfoBean.setName(userInfo.get(0).getName());
    			userInfoBean.setEmail(userInfo.get(0).getEmail());
    			userInfoBean.setPhone(userInfo.get(0).getPhone());
    			userInfoBean.setRollNo(userInfo.get(0).getRollNo());
    			userInfoBean.setRoleBean(userInfo.get(0).getRole());
    			userInfoBean.setBranch(userInfo.get(0).getBranch());
	        }
		 }
    	return userInfoBean;
    }
    
    public Boolean issueBook(String userId,String bookId){
    	
    	Boolean status=false;
    	BookDetail bookDetail = (BookDetail) commonDao.find(BookDetail.class, Long.parseLong(bookId));
    	UserProfile userProfileDetail = (UserProfile) commonDao.find(UserProfile.class,  Long.parseLong(userId));
    	
    	Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
    	BookIssued bookIssued = new BookIssued();
    	bookIssued.setBookDetail(bookDetail);
    	bookIssued.setUserProfile(userProfileDetail);
    	bookIssued.setIssuedDate(calendar.getTime());
    	calendar.add(Calendar.DATE, 14);
    	bookIssued.setSupposedReturnDate(calendar.getTime());
    	bookIssued.setFine(0);
    	bookIssued.setIsReturned(Long.parseLong("0"));
    	try{
    		commonDao.create(bookIssued);
    		status=true;
    	}
    	catch(Exception e){
    		System.out.println(e);
    		status=false;
    	}
    	return status;
    }
    
    public Boolean changeQuantity(String quantity,String bookId){
    	Boolean status=false;
    	BookDetail bookDetail = (BookDetail) commonDao.find(BookDetail.class, Long.parseLong(bookId));
    	bookDetail.setQuantity(Integer.parseInt(quantity));
    	try{
    		commonDao.create(bookDetail);
    		status=true;
    	}
    	catch(Exception e){
    		System.out.println(e);
       	}
    	return status;
    }  
    
    public List<BookIssuedUserDetailBean> getBookIssueUsers(String bookId){
    	String query="select p from BookIssued p where book_id='"+bookId+"' and is_returned=0";
		List<BookIssued> bookIssuedList =  (List<BookIssued>) commonDao.executeNativeQuery(query);
		List<BookIssuedUserDetailBean> bookIssuedUserDetailList= new ArrayList<BookIssuedUserDetailBean>();
				
			
		for(BookIssued bookIssued : bookIssuedList){
			BookIssuedUserDetailBean bookIssuedUserDetailBean = new BookIssuedUserDetailBean();
			bookIssuedUserDetailBean.setUserDetails(bookIssued.getUserProfile());
			bookIssuedUserDetailBean.setIssuedDate(bookIssued.getIssuedDate());
			bookIssuedUserDetailBean.setLastReturnDate(bookIssued.getSupposedReturnDate());
			bookIssuedUserDetailBean.setId(bookIssued.getId());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			Date todayDate=calendar.getTime();
			if(todayDate.after(bookIssued.getSupposedReturnDate())){
				long diff = todayDate.getTime() - bookIssued.getSupposedReturnDate().getTime();
				bookIssuedUserDetailBean.setFine(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
			}else{
				bookIssuedUserDetailBean.setFine(0L);
			}
			bookIssuedUserDetailList.add(bookIssuedUserDetailBean);
		}
		return bookIssuedUserDetailList;
    }
    
    public Boolean returnedBook(String bookIssuedId,String fine){
		Boolean status= false;
    	BookIssued bookIssued = (BookIssued) commonDao.find(BookIssued.class,  Long.parseLong(bookIssuedId));
		bookIssued.setFine(Integer.parseInt(fine));
		bookIssued.setIsReturned(1L);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		bookIssued.setActualReturnDate(calendar.getTime());
		try{
			commonDao.update(bookIssued);
			status = true;
		}
		catch(Exception e){
			System.out.println(e);
		}
		return status;
    }
    
    public Boolean updateDetails(String userId,String email,String phone){
		Boolean status= false;
		UserProfile userProfile = (UserProfile) commonDao.find(UserProfile.class, Long.parseLong(userId));
		userProfile.setEmail(email);
		userProfile.setPhone(phone);
		try{
			commonDao.update(userProfile);
			status = true;
		}
		catch(Exception e){
			System.out.println(e);
		}
		return status;
    }
    
    
    
    public List<BookIssued> userCurrentIssuedBookHistory(String userId){
    	String query="select p from BookIssued p where userProfile='"+userId+"' and is_returned=0";
		List<BookIssued> bookIssuedList =  (List<BookIssued>) commonDao.executeNativeQuery(query); 
    	return bookIssuedList;
    }
    public List<BookIssued> userOldIssuedBookHistory(String userId){
    	String query="select p from BookIssued p where userProfile='"+userId+"' and is_returned=1";
		List<BookIssued> bookIssuedList =  (List<BookIssued>) commonDao.executeNativeQuery(query); 
    	return bookIssuedList;
    }
    
    public String sendEmail(String emailTo){  
        //Get properties object 
    	String status ="notFound";
    	String query="select p from UserProfile p where email='"+emailTo+"'";
		List<UserProfile> userProfile = (List<UserProfile>) commonDao.executeNativeQuery(query); 
		if(userProfile.size() != 0){
			status ="notSent";
			final String username = "kclibrary880@gmail.com";
			final String password = "123@library";
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587"); 
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {
				Message message = new MimeMessage(session);
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(emailTo));    
	            message.setSubject("no-reply@kc-library-team");    
	            String userDetail="UserName :"+ emailTo +" Password:" + userProfile.get(0).getPassword()+"";
	            message.setText(userDetail);    
	           //send message  
	            Transport.send(message);    
	            status ="sent";
	            System.out.println("mail sent successfully"); 
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}else{
			status ="notFound";
		}
        return status;     
     } 
}

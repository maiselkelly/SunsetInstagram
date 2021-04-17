import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import daos.CommentDAO;
import daos.FollowsDAO;
import daos.ImageDAO;
import daos.LikesDAO;
import daos.RootDAO;
import daos.TagsDAO;
import daos.UserDAO;
import entityTypes.Comment;
import entityTypes.Follow;
import entityTypes.Image;
import entityTypes.Like;
import entityTypes.User;
import entityTypes.Tag;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.PreparedStatement;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */

public class ControlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String mymsg;
    private UserDAO userDAO;
    private CommentDAO commentDAO;
    private ImageDAO imageDAO;
    private LikesDAO likesDAO;
    private FollowsDAO followsDAO;
    private TagsDAO tagsDAO;
    private RootDAO rootDAO;
    private int imageForComment;
    private String imagePoster;


    private HttpSession session = null;
 
    public void init() {
       
        userDAO = new UserDAO();
        commentDAO = new CommentDAO();
        imageDAO = new ImageDAO();
        likesDAO = new LikesDAO();
        followsDAO = new FollowsDAO();
        tagsDAO = new TagsDAO();
        rootDAO = new RootDAO();
 
    }
 
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("in control servlet dopost");
        
        doGet(request, response);
    }
 
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        System.out.println("in control servlet doget");
        
        response.setContentType("text/html");
		
		

        try {
        	RequestDispatcher dispatcher;
        	
            switch (action) {        
		
			//Will Insert a New Sunset Image "Insert"	 
            case "/insertSunset":
            	insertImage(request, response);
            	break;
            	
           //Will show sessions user's images
            case "/sessionImage":	
                System.out.println("Inside Session Images");
            	displaySessionImages(request, response);
            	break;
            
           //Will list all images in the feed "List all" 	
            case "/imageFeed":
                System.out.println("The action is: list");
            	imageFeed(request, response);
        		break;	        		
           
			//This inserts a new user to the database	
            case "/insert":
            	insertUser(request, response);
                break;              
                
            //Calls the registration functions    
            case "/register":
            	registerUser(request, response);
                break;                    
                
            //Calls the login functions    
            case "/login":
            	userLogin(request, response);
            	break;            	
            	
            	  //This will update the User   
            case "/update":
            	updateUser(request, response);
                break;          	
            	
            	//This will be what we can use to initialize the user
            case "/InitializeDatabase":
            	System.out.println("Inside Initialize!");
               	initializeDatabase(request, response);
               	System.out.println("Comepleted initialize");
            	break;
            	
            //Lists all users in the system
            case "/listUsers":
            	System.out.println("The action is: list users");
              	listUsers(request, response);
          		break;	
          		
            //This will post the users from the search feed:
            case "/searchUsersPosting":
            	System.out.println("The action is: search users");
              	searchUsersPosting(request, response);
          		break;	          		
                
          	//Grabs a user and displays their profile information
            case "/getUsers":
                System.out.println("GetUser profile information");
                getUser(request, response);
            	
             //This will call to the edit page where a user can edit their image
            case "/edit":
                imageUpdateForm(request, response);
                break;
                
             	 //This will update the Image   
            case "/updateImage":
            	updateImage(request, response);
                break;   
            
              //This will delete the Image  
            case "/deleteImage":
            	deleteImage(request, response);
            	break;   
            	
            //Inserts a follower to the user
            case "/insertFollower":
               insertFollower(request, response);
               break;
               
            //Deletes a follower to the user
            case "/deleteFollower":   
            	deleteFollower(request, response);
            	break;
            
            //Inserts a follower to the user
            case "/like":
               like(request, response);
               break;
               
            //Deletes a follower to the user
            case "/unlike":   
            	unlike(request, response);
            	break;             
            
            
            case "/insertComment":
            	insertComment(request, response);
                break;
                
            case "/commentForm":
            	commentForm(request, response);
                break; 
                
                
                //Project 3 Queries 
            case "/Data":
            	displayData(request, response);
            	break;            
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    

    //Initialize Database
    private void initializeDatabase(HttpServletRequest request, HttpServletResponse response)
    	throws SQLException, IOException, ServletException{
    	
    	//Drops Tables
    	tagsDAO.dropTable();
		commentDAO.dropTable();
		followsDAO.dropTable();
		likesDAO.dropTable();
		imageDAO.dropTable();
		userDAO.dropTable();

    	
    	//Initializes all databases
    	userDAO.initUserTable();
    	imageDAO.initImageTable(); 
    	commentDAO.initCommentTable();  
    	likesDAO.initLikesTable();  
    	followsDAO.initFollowsTable();  
    	tagsDAO.initTagsTable();
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");         	
        dispatcher.forward(request, response);
 
    }
    
    
    
   //Login Page Function - Has Registered user login
    private void userLogin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {      
        
    	String email = request.getParameter("email");
    	String firstname = request.getParameter("firstname");
    	String password = request.getParameter("password");
    	
    	
    	// checks if is our root user OR at least a registered user
    			if ((email.equals("email@mail.com") && password.equals("pass1234")) || userDAO.isUserValid(email, password)) {
    				session = request.getSession();
    				session.setAttribute("currentUser", email);
    				session.setMaxInactiveInterval(15 * 60);// auto logout - accepts argument in seconds

    				// if user is root, send to rootHome.jsp, other registered users go to regular
    				// home.jsp
    				if (email.equals("email@mail.com")) {
    					System.out.println("We have the root user in controlservlet.userlogin()");
    					displayData(request, response);
    				} else {
    					followsDAO.insertFollower(new Follow(email, email)); //ensure user is following self
    					response.sendRedirect("imageFeed");
    				}
    				// failed logins handled here
    			} else {
    				request.setAttribute("loginError", "Invalid username or password");
    				RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
    				dispatcher.forward(request, response);
    			}
    		}

   
    //Register Page - Has new user register to the website
    private void registerUser(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {      
        RequestDispatcher dispatcher = request.getRequestDispatcher("Registration.jsp");       
        
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        
        if(!userDAO.duplicatedUser(email)) {
        	User user = new User(firstname, lastname, email, password, gender, birthday);
        	userDAO.insertUser(user);
        	dispatcher = request.getRequestDispatcher("Login.jsp");
        	dispatcher.forward(request, response);
        }else {
        	request.setAttribute("RegistrationError", "This email is already taken");
        	dispatcher = request.getRequestDispatcher("Registration.jsp");
        	dispatcher.forward(request, response);
        }
   
    }
    

    //Inserts a New User into the database
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        
        User newUsers = new User(firstname, lastname, email, password, gender, birthday);
        userDAO.insertUser(newUsers);
        response.sendRedirect("index");  // The sendRedirect() method works at client side and sends a new request
        
        System.out.println("Inserted User");
    }
 
    
    //for inserting comment
    private void commentForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
    	
    	imageForComment = Integer.parseInt(request.getParameter("imageid"));
    	Image image = imageDAO.getImage(imageForComment);
    	imagePoster = image.getPostuser();
    	Image existingImage = imageDAO.getImage(imageForComment);
    	
        RequestDispatcher dispatcher = request.getRequestDispatcher("sunsetFeed");  
    	request.setAttribute("comment", existingImage);
    	dispatcher.forward(request, response);   	
    }
    


    //Lists all of the users in the database
    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
     
        List<User> listUsers = userDAO.listAllUsers();
        request.setAttribute("listUsers", listUsers);       
        RequestDispatcher dispatcher = request.getRequestDispatcher("Community.jsp");       
        dispatcher.forward(request, response);
     
    }
    
    
    
    //Lists all of the users in the database after search
    private void searchUsersPosting(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	
    	String searchFor = request.getParameter("searchFor");
    	System.out.println("name to search is : "+ searchFor);
    	
    	if(searchFor == "") {
    		request.setAttribute("searchEmpty", "Please enter a first or last name");
    	}
    	else {
    		List<User> userResults = userDAO.searchName(searchFor);
    		 request.setAttribute("searchResults", userResults);
    		 RequestDispatcher dispatcher = request.getRequestDispatcher("SearchResults.jsp");
    		 dispatcher.forward(request, response);
    	}
    	
    }
    
    
    //Get User information
    private void getUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
  	
    	User getUser = userDAO.getUser("email");
    	request.setAttribute("getUser", getUser);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("UserProfilePage.jsp");
    	dispatcher.forward(request, response);
    		
    }
   
    
    //Updates any part of the user information
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        System.out.println("updating user: " + id);
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
       
        User newUsers = new User(firstname, lastname, email, password, gender, birthday);
        userDAO.updateUser(newUsers);
        response.sendRedirect("index");       
        response.sendRedirect("list");
    }
    
 
    //Lists all of the sunsets
    private void imageFeed(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	
    	List<Image> imageFeed = imageDAO.imageFeed(String.valueOf(session.getAttribute("currentUser"))); //fetches images only of current users followers

    	for(int i=0; i<imageFeed.size(); i++) {
    		imageFeed.get(i).setComments(commentDAO.commentFeed(imageFeed.get(i).getImageId()));		
    		List<Tag> tagFeed = (tagsDAO.tagFeed(imageFeed.get(i).getImageId()));
    		imageFeed.get(i).setTags(tagFeed);
    	}
    
    	request.setAttribute("imageFeed", imageFeed);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("sunsetFeed.jsp");
    	dispatcher.forward(request, response);
    
    }
    
    
    //Display Session User Images
    private void displaySessionImages(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	
    	String postuser = String.valueOf(session.getAttribute("currentUser"));
    	List<Image> listImages = imageDAO.searchUser(postuser);

        request.setAttribute("sessionImage", listImages);       
        RequestDispatcher dispatcher = request.getRequestDispatcher("Postsunset.jsp");     
        dispatcher.forward(request, response);
    }
    
    //Insert a new sunset Image
    private void insertImage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	String datestamp = getCurrentDate();
    	String url = request.getParameter("imageURL");
    	String description = request.getParameter("description");
    	String tagString = request.getParameter("tags");
    	String postuser = String.valueOf(session.getAttribute("currentUser"));
    	
    	Image newImage = new Image(datestamp, url, description, postuser);
    	int newImageId = imageDAO.insertImage(newImage);
    	tagsDAO.insertTags(newImageId, tagString);
    	response.sendRedirect("imageFeed");    	
    }
    
    
    private void insertComment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	
    	
    	int imageid = Integer.parseInt(request.getParameter("imageid"));
    	String email = String.valueOf(session.getAttribute("currentUser"));
        String comment = request.getParameter("comment");

        Comment newComment = new Comment(imageid, email, comment);
        commentDAO.insertComment(newComment);
        
        System.out.println("Inserted Comment: " + newComment.toString());
        
        response.sendRedirect("imageFeed");  // The sendRedirect() method works at client side and sends a new request  
    }
    
    
    //Updates a Sunset Image
    private void updateImage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
     
        int imageid = Integer.parseInt(request.getParameter("imageid"));
    	String currentDate = getCurrentDate();
    	String url = request.getParameter("imageURL");
    	String description = request.getParameter("description");
    	String postuser = String.valueOf(session.getAttribute("currentUser"));
    	
        Image image = new Image(imageid, currentDate, url, description, postuser);
        imageDAO.updateImage(image);
        response.sendRedirect("sessionImage");     
    }
    
    
    //Form to Update a Sunset Image
    private void imageUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
     
        int imageid = Integer.parseInt(request.getParameter("imageid"));        
        Image existingImage = imageDAO.getImage(imageid);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditSunsetImagesForm.jsp");
        request.setAttribute("imageid", existingImage);
        dispatcher.forward(request, response); // The forward() method works at server side, and It sends the same request and response objects to another servlet.      
    }
    
    //Deletes a sunset Image
    private void deleteImage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
     
        int imageid = Integer.parseInt(request.getParameter("imageid"));
       
        imageDAO.deleteImage(imageid);
        response.sendRedirect("sessionImage"); 
     
    }
    
    
    //Deletes a followed user
    public void deleteFollower(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
    	
    	String user = String.valueOf(session.getAttribute("currentUser"));
    	String following = request.getParameter("followingemail");
    	//don't allow user to unfollow self
    	if(!user.equals(following)) {
    		followsDAO.deleteFollower(user, following);
    	}    	
    	response.sendRedirect("listUsers");
    }
    
    
    //Inserts a followed user to current user
    public void insertFollower(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
    	
    	String user = String.valueOf(session.getAttribute("currentUser"));
    	String following = request.getParameter("followingemail");
    	Follow newFollow = new Follow(user, following);
    	System.out.println(user+" &" +following + "  vs  " + newFollow.toString());
    	followsDAO.insertFollower(newFollow);
    	response.sendRedirect("listUsers");
    }
    
    //inserts a like using image ID and current user
    public void like(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
    	String user = String.valueOf(session.getAttribute("currentUser"));
    	int imageid = Integer.parseInt(request.getParameter("imageid"));
    	Like newLike = new Like(imageid, user, getCurrentDate());
    	System.out.println( user + " likes image: " +imageid);
    	likesDAO.insert(newLike);
    	response.sendRedirect("imageFeed");
    }
    
    //unlikes an image based on image id and current user
    public void unlike(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
    	String user = String.valueOf(session.getAttribute("currentUser"));
    	int imageid = Integer.parseInt(request.getParameter("imageid"));
    	Like newLike = new Like(imageid, user, getCurrentDate());
    	System.out.println( user + " UNlikes image: " +imageid);
    	likesDAO.remove(newLike);
    	response.sendRedirect("imageFeed");
    }
    
    //Gets the current Date for date stamp
    private String getCurrentDate() {
    	LocalDate today = LocalDate.now();
    	String currentDate = today.toString();
    	return currentDate;
    	//optionally can be converted to a single line:
    	//return LocalDate.now().toString();
    }
    
    //Gets the current Time for time stamp
    private String getCurrentTime() {
    	LocalTime current = LocalTime.now();
    	return current.toString();
    }
    
    
    //Displaying Data
    public void displayData(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	
    	//Cool Images
    	List<String> displayCoolImages = rootDAO.coolImages();
    	request.setAttribute("displayCoolImages", displayCoolImages);
    	
    	for (int i =0; i<displayCoolImages.size(); i++)
    		System.out.println("cool2: " + displayCoolImages.get(i));
    	
    	//New Images
    	String postdate = getCurrentDate();
    	List<String> displayNewImages = rootDAO.newImages(postdate);
        request.setAttribute("displayNewImages", displayNewImages); 
        
        for (int i =0; i<displayNewImages.size(); i++)
    		System.out.println("got new 2: " + displayNewImages.get(i));
        
      //  Viral Images
    	List<String> displayViralImages = rootDAO.viralImages();
        request.setAttribute("displayViralImages", displayViralImages);  
        
        for (int i =0; i<displayViralImages.size(); i++)
    		System.out.println("got Viral 2: " + displayViralImages.get(i));
        
        //Top Users
        List<String> displayTopUsers = rootDAO.topUsers();
        request.setAttribute("displayTopUsers", displayTopUsers); 
    
    	//Popular Users
    	List<String> displayPopularUsers = rootDAO.popularUsers();
        request.setAttribute("displayPopularUsers", displayPopularUsers);
        
        //Common Users
        List<User> allUsers = userDAO.listAllUsers();
        request.setAttribute("allUsers", allUsers);
        for (int i =0; i<allUsers.size(); i++)
        	System.out.println(allUsers.get(i).toString());
        
        List<String> commonUsers;
        if(request.getParameter("name1") != null  && request.getParameter("name2") != null) {
        	String name1 = String.valueOf(request.getParameter("name1") );
        	String name2 = String.valueOf(request.getParameter("name2"));
        	commonUsers = rootDAO.commonUsers(name1, name2);
        }else {
        	commonUsers = rootDAO.commonUsers("email@mail.com", "email@mail.com");
       
        }
        request.setAttribute("commonUsers", commonUsers);
        
        //Top Tags
    	List<String> displayTopTags = rootDAO.topTags();
        request.setAttribute("displayTopTags", displayTopTags);  
    	
        //Positive Users
    	List<String> positiveUsers = rootDAO.positiveUsers();
        request.setAttribute("positiveUsers", positiveUsers);
        
        //Positive Users
    	List<String> poorImages = rootDAO.poorImages();
        request.setAttribute("poorImages", poorImages);
        
        //Inactive Users
    	List<String> inactiveUsers = rootDAO.inactiveUsers();
        request.setAttribute("inactiveUsers", inactiveUsers);
    	
    	 RequestDispatcher dispatcher = request.getRequestDispatcher("rootHome.jsp");     
         dispatcher.forward(request, response);       
         
    }

    
  
}

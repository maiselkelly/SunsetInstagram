CSC 4710 - Project 1
Kelly Maisel(em0608) and Lloyd Brombach (as7923)


Installation Instructions
1. Download and install Tomcat 9 from https://tomcat.apache.org/download-90.cgi
2. Open Eclipse, and go to Window -> Show View -> Servers. Click Apache and add the Tomcat 9.0 server from where it is stored.
3. Download the file from where it is saved.
4. Import the file from the archive and open the project. 
5. Create a MySQL server "project" with a root user "john" and password "pass1234"
6. Click on the web file, "register" and open in the server. This will allow you to see the registration page. 
7. If you are the root user and want to initialize the database, login as "email@mail.com" password "pass1234"

Project Part 1 does the following:
 The website root user has a username “root” and password “pass1234” For the root user, implement a button called “Initialize Database”. 
 This button will only be displayed after the root user signed in successfully. Each time the root user clicks the “Initialize Database”
  button,  the following three things will happen: 1) all existing tables, if any, will be dropped; 2)  all  necessary  tables  will  be  
  created  or  recreated  automatically;  and  3)  each  table  will  be inserted with at least 10 realistic tuples (which can be hard-coded 
  in your Java program), so that  each  query  below  will  return  some  meaningful  results.
  
   Implement a user registration and login interface so that only a registered user can login into the  system.  Sign  up  for  a  new  user  
   with  information  such  as:  username  (which  is  an  email), password,  password  confirmed,  first  name,  last  name,  age.  Duplicate  
   username  should  be detected and fail the signup. Unmatching passwords should be detected as well. 
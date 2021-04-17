package entityTypes;

public class User {
		protected int id;
	    protected String firstname;
	    protected String lastname;
	    protected String email;
	    protected String password;
	    protected String gender;
	    protected String birthday;
	    

	 
	    public User() {
	    }
	 
	    
	    public User(int id, String firstname, String lastname, String email, String password, String gender, String birthday) {
	    	this.id = id;
	    	this.firstname = firstname;
	        this.lastname = lastname;
	        this.email = email;
	        this.password = password;
	        this.gender = gender;
	        this.birthday = birthday;
	    }
	     
	    public User(String firstname, String lastname, String email, String password, String gender, String birthday) {
	        this.firstname = firstname;
	        this.lastname = lastname;
	        this.email = email;
	        this.password = password;
	        this.gender = gender;
	        this.birthday = birthday;
	    }
	 
	    
	    public User(String email, String password) {
	    	this.email = email;
	    	this.password = password;
	    }
	    
	    
	    public int getId() {
	    	return id;
	    }
	    
	    public void setId(int id) {
	    	this.id = id;
	    }
	 
	    public String getFirstName() {
	        return firstname;
	    }
	 
	    public void setFirstName(String firstname) {
	        this.firstname = firstname;
	    }
	 
	    public String getLastName() {
	        return lastname;
	    }
	 
	    public void setLastName(String lastname) {
	        this.lastname = lastname;
	    }
	 
	    public String getPassword() {
	        return password;
	    }
	 
	    public void setPassword(String password) {
	        this.password = password;
	    }
	    
	    public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getGender() {
	        return gender;
	    }
	 
	    public void setGender(String gender) {
	        this.gender = gender;
	    }
	    
	    public String getBirthday() {
	        return birthday;
	    }
	 
	    public void setBirthday(String birthday) {
	        this.birthday = birthday;
	    }


		@Override
		public String toString() {
			return "User [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", password="
					+ password + ", gender=" + gender + ", birthday=" + birthday + "]";
		}
	    
	    
	}




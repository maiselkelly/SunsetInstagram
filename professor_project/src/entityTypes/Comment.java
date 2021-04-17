package entityTypes;

public class Comment {
	
	protected int image_id;
	protected String email;
    protected String comment;
    protected String timestamp;
    
 
    
    public Comment() {
    }
 

  
    public Comment(int image_id, String email, String comment) {
    	this.image_id = image_id;
    	this.email =email;
    	this.comment = comment;
    }
    
  
 
    public int getImage_id() {
		return image_id;
	}



	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}



	public String getComment() {
        return comment;
    }
 
    public void setComment(String comment) {
        this.comment = comment;
    }
 
        
    public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}



	public String getTimestamp() {
        return timestamp;
    }
 
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }



	@Override
	public String toString() {
		return "Comment [image_id=" + image_id + ", email=" + email + ", comment=" + comment + ", timestamp="
				+ timestamp + "]";
	}





    
    

}

package entityTypes;

import java.util.List;

public class Image {
	
	protected int imageid;
    protected String postdate;
    protected String posttime;
    protected String url;
    protected String description;
    protected String postuser;
    protected int numLikes;
    protected List<Comment> comments;
    protected List<Tag> tags;
    
    
    public Image() {
    	
    }
    
    public Image(int imageid) {
        this.imageid = imageid;
    }
    
 //KELLY EDITIED THIS 3/6
 public Image(String postdate, String url, String description) {
 	this.postdate = postdate;
 	this.url = url;
 	this.description = description;
 	
 	
 }
 
 public Image(int imageid, String postdate, String url, String description, String postuser) {
	 
	 this.imageid=imageid;
	 this.postdate =postdate;
	 this.url = url;
	 this.description = description;
	 this.postuser = postuser;
 }

    public Image(String postdate, String url, String description, String postuser) {
    	this.url = url;
    	this.description = description;
    	this.postdate = postdate;
    	this.postuser = postuser;
    }
    
    public Image(String postdate, String url, String description, String postuser, String posttime) {
    	
    	this.postdate = postdate;
    	this.posttime = posttime;
    	this.url = url;
    	this.description = description;
    	this.postuser = postuser;
    }
  
    public Image(int imageid, String postdate, String posttime, String url, String description, String postuser) {
    	this(postdate, posttime, url, description, postuser);
    	this.imageid = imageid;
    }
  
    

	public int getImageId() {
		return imageid;
	}

	public void setImageid(int imageid) {
		this.imageid = imageid;
	}
	
	public int getNumLikes() {
		return numLikes;
	}

	public void setNumLikes(int numLikes) {
		this.numLikes = numLikes;
	}
	
    public String getPostDate() {
        return postdate;
    }
 
    public void setPostDate(String postdate) {
        this.postdate = postdate;
    }
 
    
    public String getPostTime() {
        return posttime;
    }
 
    public void setPostTime(String posttime) {
        this.posttime = posttime;
    }
 
    
    public String getUrl() {
        return url;
    }
 
    public void setUrl(String url) {
        this.url = url;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getPostuser() {
        return postuser;
    }
 
    public void setPostuser(String postuser) {
        this.postuser = postuser;
    }

	public String getPostdate() {
		return postdate;
	}

	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}

	public String getPosttime() {
		return posttime;
	}

	public void setPosttime(String posttime) {
		this.posttime = posttime;
	}


	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	
	
	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	
	
	
	@Override
	public String toString() {
		return "Image [imageid=" + imageid + ", postdate=" + postdate + ", posttime=" + posttime + ", url=" + url
				+ ", description=" + description + ", postuser=" + postuser + ", numLikes=" + numLikes + "]";
	}
    
    
}

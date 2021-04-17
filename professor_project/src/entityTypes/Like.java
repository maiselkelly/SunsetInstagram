package entityTypes;

public class Like {
	private String likerId;
	private int imageId;
	private String likeDate;



	public Like(int imageId, String likerId, String likeDate) {
		this.imageId = imageId;
		this.likerId = likerId;
		this.likeDate = likeDate;
	}


	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	
	public String getLikerId() {
		return likerId;
	}

	public void setLikerId(String likerId) {
		this.likerId = likerId;
	}

	public String getLikeDate() {
		return likeDate;
	}

	public void setLikeDate(String likeDate) {
		this.likeDate = likeDate;
	}


	@Override
	public String toString() {
		return "Like [likerId=" + likerId + ", imageId=" + imageId + ", likeDate=" + likeDate + "]";
	}
	
	

}

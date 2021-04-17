package entityTypes;

public class Follow {
	
	private String follower;
	private String followed;
	
	
	public Follow(String follower, String followed) {
		this.follower = follower;
		this.followed = followed;
	}
	
	
	public String getFollower() {
		return follower;
	}
	public void setFollower(String follower) {
		this.follower = follower;
	}
	public String getFollowed() {
		return followed;
	}
	public void setFollowed(String followed) {
		this.followed = followed;
	}


	@Override
	public String toString() {
		return "Follow [follower=" + follower + ", followed=" + followed + "]";
	}


	
}

package entityTypes;

public class Tag {

	private int imageId;

	private String tag;

	private Tag() {
	}

	public Tag(int imageId, String tag) {
		this.imageId = imageId;
		this.tag = tag;
		System.out.println(this.toString());
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "Tag [imageId=" + imageId + ", tag=" + tag + "]";
	}

	
	
}

package dk.rohdef.rohdeBusinessCard.model;

public class Resource {
	private ResourcesType type;
	private String url; 
	
	public ResourcesType getType() {
		return type;
	}
	public void setType(ResourcesType type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}

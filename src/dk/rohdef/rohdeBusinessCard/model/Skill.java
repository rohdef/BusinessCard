package dk.rohdef.rohdeBusinessCard.model;

import dk.rohdef.rohdeBusinessCard.IHasId;


public class Skill implements IHasId{
	private String name, description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getId() {
		return name;
	}
}
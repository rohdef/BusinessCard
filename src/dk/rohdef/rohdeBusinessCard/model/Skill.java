package dk.rohdef.rohdeBusinessCard.model;

import dk.rohdef.rohdeBusinessCard.IHasId;


public class Skill implements IHasId{
	private String name, description;
	private SkillType skillType;
	private String[] projects;

	public String[] getProjects() {
		return projects;
	}

	public SkillType getSkillType() {
		return skillType;
	}

	public void setSkillType(SkillType skillType) {
		this.skillType = skillType;
	}

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
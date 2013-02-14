package dk.rohdef.rohdeBusinessCard.model;

import java.util.Date;

import dk.rohdef.rohdeBusinessCard.IHasId;

public class Project implements IHasId {
	private String name, shortDescription, fullDescription;
	private TeamWork teamWork;
	private Date startDate, endDate;
	private String[] skills;
	private String[] references;

	public Project() {
	}
	
	public String getName() {
		return name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public String getFullDescription() {
		return fullDescription;
	}

	public TeamWork getTeamWork() {
		return teamWork;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String[] getSkills() {
		return skills;
	}

	public String[] getReferences() {
		return references;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public void setTeamWork(TeamWork teamWork) {
		this.teamWork = teamWork;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setSkills(String[] skills) {
		this.skills = skills;
	}

	public void setReferences(String[] references) {
		this.references = references;
	}

	@Override
	public String toString() {
		return "Project [name=" + name + ", shortDecription=" + shortDescription
				+ "]";
	}

	@Override
	public String getId() {
		return name;
	}
}

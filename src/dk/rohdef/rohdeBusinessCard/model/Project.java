package dk.rohdef.rohdeBusinessCard.model;

import java.util.Date;

import dk.rohdef.rohdeBusinessCard.IHasId;

public class Project implements IHasId {
	private String name, shortDecription, fullDescription;
	private TeamWork teamWork;
	private Date startDate, endDate;
	private Skill[] skills;
	private Person[] references;

	public Project() {
	}
	
	public String getName() {
		return name;
	}

	public String getShortDecription() {
		return shortDecription;
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

	public Skill[] getSkills() {
		return skills;
	}

	public Person[] getReferences() {
		return references;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setShortDecription(String shortDecription) {
		this.shortDecription = shortDecription;
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

	public void setSkills(Skill[] skills) {
		this.skills = skills;
	}

	public void setReferences(Person[] references) {
		this.references = references;
	}

	@Override
	public String toString() {
		return "Project [name=" + name + ", shortDecription=" + shortDecription
				+ "]";
	}

	@Override
	public String getId() {
		return name;
	}
}

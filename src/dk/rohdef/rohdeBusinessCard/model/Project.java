package dk.rohdef.rohdeBusinessCard.model;

import java.util.Date;
import java.util.List;

public class Project {
	private int id;
	
	private String name, shortDecription, fullDescription;
	private TeamWork teamWork;
	private Date startDate, endDate;
	private List<Skill> skills;
	private List<Person> references;

	public Project() {
	}
	
	public int getId() {
		return id;
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

	public List<Skill> getSkills() {
		return skills;
	}

	public List<Person> getReferences() {
		return references;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public void setReferences(List<Person> references) {
		this.references = references;
	}
}

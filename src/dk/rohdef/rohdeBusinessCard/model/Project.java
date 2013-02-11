package dk.rohdef.rohdeBusinessCard.model;

import java.util.Date;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = "projects")
public class Project {
	@DatabaseField (generatedId=true)
	private int id;
	
	@DatabaseField
	private String name, shortDecription, fullDescription;
	
	@DatabaseField
	private TeamWork teamWork;
	
	@DatabaseField
	private Date startDate, endDate;
	
	@ForeignCollectionField
	private ForeignCollection<Skill> skills;
	
	@ForeignCollectionField
	private ForeignCollection<ReferencedPerson> references;

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

	public ForeignCollection<Skill> getSkills() {
		return skills;
	}

	public ForeignCollection<ReferencedPerson> getReferences() {
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

	public void setSkills(ForeignCollection<Skill> skills) {
		this.skills = skills;
	}

	public void setReferences(ForeignCollection<ReferencedPerson> references) {
		this.references = references;
	}
}

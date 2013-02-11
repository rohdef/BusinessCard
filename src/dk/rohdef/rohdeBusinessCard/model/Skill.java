package dk.rohdef.rohdeBusinessCard.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = "skills")
public class Skill {
	@DatabaseField (generatedId = true)
	private int id;
	
	@DatabaseField
	private String name, description;
	
	@DatabaseField(foreign = true)
	private Project project;
}
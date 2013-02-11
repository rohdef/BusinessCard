package dk.rohdef.rohdeBusinessCard.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="referenced_person")
public class ReferencedPerson extends Person {
	@DatabaseField(foreign=true)
	private Project project;
}

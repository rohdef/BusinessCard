package dk.rohdef.rohdeBusinessCard.activity;

import java.util.HashMap;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import dk.rohdef.rohdeBusinessCard.R;
import dk.rohdef.rohdeBusinessCard.model.Person;
import dk.rohdef.rohdeBusinessCard.model.Project;
import dk.rohdef.rohdeBusinessCard.model.Skill;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String projectsJson = "[" +
				"" +
				"{\"name\":\"Klubhaeftet\"," +
				"\"shortDescription\":\"Skidt\"," +
				"\"fullDescription\":\"Meget meget skidt\"," +
				"\"teamWork\":\"solo\"," +
				"\"startDate\":null," +
				"\"endDate\":null," +
				"\"skills\":[]," +
				"\"references\":[]}," +
				
				"{\"name\":\"Proj 2\"," +
				"\"shortDescription\":\"Godt\"," +
				"\"fullDescription\":\"Juhuu det var rart\"," +
				"\"teamWork\":\"solo\"," +
				"\"startDate\":null," +
				"\"endDate\":null," +
				"\"skills\":[]," +
				"\"references\":[]}," +
				
				"{\"name\":\"Livet\"," +
				"\"shortDescription\":\"Interessant\"," +
				"\"fullDescription\":\"Der sker saa meget baade godt og skidt\"," +
				"\"teamWork\":\"solo\"," +
				"\"startDate\":null," +
				"\"endDate\":null," +
				"\"skills\":[]," +
				"\"references\":[]}]";
		
		Gson gson = new Gson();
		Project[] projects = gson.fromJson(projectsJson, Project[].class);
		
		String referencesJson = "";
		Person[] references = gson.fromJson(referencesJson, Person[].class);
		
		String skillsJson = "";
		Skill[] skills = gson.fromJson(skillsJson, Skill[].class);
		
		HashMap<String, Project> projectMap = new HashMap<String, Project>();
		HashMap<String, Person> referenceMap = new HashMap<String, Person>();
		HashMap<String, Skill> skillMap = new HashMap<String, Skill>();
		
		for (Project project : projects) {
			projectMap.put(project.getName(), project);
		}
		
		for (Person reference : references) {
			referenceMap.put(reference.getEmail(), reference);
		}
		
		for (Skill skill : skills) {
			skillMap.put(skill.getName(), skill);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void onContactClick(View contactButton) {
		Intent intent = new Intent(contactButton.getContext(), Contact.class);
		startActivity(intent);
	}
	
	public void onProjectsClick(View projectsButton) {
		Intent intent = new Intent(projectsButton.getContext(), Projects.class);
		startActivity(intent);
	}
}

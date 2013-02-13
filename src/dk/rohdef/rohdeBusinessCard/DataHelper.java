package dk.rohdef.rohdeBusinessCard;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import dk.rohdef.rohdeBusinessCard.model.Person;
import dk.rohdef.rohdeBusinessCard.model.Project;
import dk.rohdef.rohdeBusinessCard.model.Skill;

public class DataHelper {
	private Activity parentActivity;

	private List<Project> projects;
	private List<Skill> skills;
	private List<Person> references;
	
	private Map<String, Project> projectMap;
	private Map<String, Person> referenceMap;
	private Map<String, Skill> skillMap;
	private Gson gson = new Gson();
	
	public DataHelper(Activity parentActivity) {
		super();
		this.parentActivity = parentActivity;
	}
	
	public Person getContactDetails() {
		getJsonData("", "contact.json");
		return null;
	}
	
	private void ensureProjects() {
		if (projectMap == null) {
			Type projectType = new TypeToken<Collection<Project>>(){}.getType();
			ArrayMapTouple<Project> projectsTouple = generateMapResourceMap("http://?", "projects.json", projectType);
			projectMap = projectsTouple.tMap;
			projects = projectsTouple.tList;
		}
	}
	public Map<String, Project> getProjectsMap() {
		ensureProjects();
		return projectMap;
	}
	public List<Project> getProjects() {
		ensureProjects();
		return projects;
	}
	
	public Map<String, Person> getReferencesMap() {
		return null;
	}
	public List<Person> getReferences() {
		return references;
	}
	
	private void ensureSkills() {
		if (skillMap == null) {
			Type skillType = new TypeToken<Collection<Skill>>(){}.getType();
			ArrayMapTouple<Skill> skillsTouple = generateMapResourceMap("http://?", "skills.json", skillType); 
			skillMap = skillsTouple.tMap;
			skills = skillsTouple.tList;
		}
	}
	public Map<String, Skill> getSkillsMap() {
		ensureSkills();
		return skillMap;
	}
	public List<Skill> getSkills() {
		ensureSkills();
		return skills;
	}
	
	private <T extends IHasId> ArrayMapTouple<T> generateMapResourceMap(String url, String file, Type type) {
		String json = getJsonData(url, file);
		
		List<T> tList = gson.fromJson(json, type);
		
		HashMap<String, T> tMap = generateMap(tList);
		
		return new ArrayMapTouple<T>(tMap, tList);
	}
	
	private <T extends IHasId> HashMap<String, T> generateMap(List<T> list) {
		HashMap<String, T> tMap = new HashMap<String, T>();
		
		for (T t : list) {
			tMap.put(t.getId(), t);
		}
		
		return tMap;
	}
	
	private String getJsonData(String url, String file) {
		String json = "";
		try {
			StringWriter writer = new StringWriter();
			InputStream in = parentActivity.openFileInput(file);
			IOUtils.copy(in, writer);
			
			json = writer.toString();
			Log.d("DataHelper", json);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	private class ArrayMapTouple<T extends IHasId> {
		Map<String, T> tMap;
		List<T> tList;
		
		public ArrayMapTouple(Map<String, T> tMap, List<T> tList) {
			super();
			this.tMap = tMap;
			this.tList = tList;
		}
	}
}

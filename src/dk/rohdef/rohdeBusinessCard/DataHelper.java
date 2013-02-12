package dk.rohdef.rohdeBusinessCard;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import dk.rohdef.rohdeBusinessCard.model.Person;
import dk.rohdef.rohdeBusinessCard.model.Project;
import dk.rohdef.rohdeBusinessCard.model.Skill;

public class DataHelper {
	private List<Project> projects;
	private List<Skill> skills;
	private List<Person> references;
	
	private Map<String, Project> projectMap;
	private Map<String, Person> referenceMap;
	private Map<String, Skill> skillMap;
	private Gson gson = new Gson();
	
	public Person getContactDetails() {
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
		if (file.equals("projects.json")) {
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
			
			return projectsJson;
		}
		
		return null;
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

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
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import dk.rohdef.rohdeBusinessCard.model.Person;
import dk.rohdef.rohdeBusinessCard.model.Project;
import dk.rohdef.rohdeBusinessCard.model.Skill;
/**
 * Provides access to the date from parsed json files. Parts of this solution is a dirty solution.
 * But chosen to avoid having multiple layers of database work to create the more correct solution.
 * This will probably be ammended in a future version.
 *  
 * @author Rohde Fischer
 */
public class DataHelper {
	private Activity parentActivity;
	private static DataHelper instance;

	private List<Project> projects;
	private List<Skill> skills;
	private List<Person> references;
	
	private Map<String, Project> projectMap;
	private Map<String, Person> referenceMap;
	private Map<String, Skill> skillMap;
	private Gson gson;
	
	private DataHelper(Activity parentActivity) {
		super();
		
		this.gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		this.parentActivity = parentActivity;
	}
	
	public static DataHelper getInstance() {
		if (instance == null) {
			throw new RuntimeException("Initiate have not been called.");
		}
		
		return instance;
	}
	
	public static void initiate(Activity parentActivity) {
		if (instance == null) {
			instance = new DataHelper(parentActivity);
			
			instance.ensureReferences();
			instance.ensureSkills();
			instance.ensureProjects();
		}
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
		return projectMap;
	}
	public List<Project> getProjects() {
		return projects;
	}
	
	private void ensureReferences() {
		if (referenceMap == null) {
			Type personType = new TypeToken<Collection<Person>>(){}.getType();
			ArrayMapTouple<Person> referencesTouple = generateMapResourceMap("http://?", "references.json", personType);
			referenceMap = referencesTouple.tMap;
			references = referencesTouple.tList;
		}
	}
	public Map<String, Person> getReferencesMap() {
		return referenceMap;
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
		return skillMap;
	}
	public List<Skill> getSkills() {
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
		
		return json;
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

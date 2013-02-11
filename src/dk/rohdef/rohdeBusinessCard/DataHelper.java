package dk.rohdef.rohdeBusinessCard;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import dk.rohdef.rohdeBusinessCard.model.Person;
import dk.rohdef.rohdeBusinessCard.model.Project;
import dk.rohdef.rohdeBusinessCard.model.Skill;

public class DataHelper {
	private HashMap<String, Project> projectMap;
	private HashMap<String, Person> referenceMap;
	private HashMap<String, Skill> skillMap;
	private Gson gson = new Gson();
	
	public Person getContactDetails() {
		return null;
	}
	
	public Map<String, Person> getReferences() {
		return null;
	}
	
	public Map<String, Project> getProjects() {
		return null;
	}
	
	public Map<String, Skill> getSkills() {
		if (skillMap == null) {
			skillMap = generateMapResourceMap("http://?", "skills.json", Skill[].class);
		}
		
		return skillMap;
	}
	
	private <T extends IHasId> HashMap<String, T> generateMapResourceMap(String url, String file, Class<T[]> klass) {
		String json = getJsonData(url, file);
		T[] tArray = gson.fromJson(json, klass);
		
		HashMap<String, T> tMap = generateMap(tArray);
		
		return tMap;
	}
	
	private <T extends IHasId> HashMap<String, T> generateMap(T[] array) {
		HashMap<String, T> tMap = new HashMap<String, T>();
		
		for (T t : array) {
			tMap.put(t.getId(), t);
		}
		
		return tMap;
	}
	
	private String getJsonData(String url, String file) {
		return null;
	}
	
	
	public void onCreate() {
//		Person person = new Person();
//		person.setFirstName("Rohde");
//		person.setLastName("Fischer");
//		person.setAddress("Kirkegårdsvej 10 D, 3.-3");
//		person.setPostal(8000);
//		person.setCity("Aarhus");
//		person.setEmail("rohdef@rohdef.dk");
//		person.setPhone("21680621");
//		
//		persDao.create(person);
//		
//		ReferencedPerson pers;
//		Project proj;
//		ForeignCollection<ReferencedPerson> referencesCollection;
//		ForeignCollection<Skill> skillCollection;
//
//		proj = new Project();
//		proj.setName("Klubhaeftet");
//		proj.setShortDecription("En mobil udgave af Klubhaeftet");
//		proj.setFullDescription("Meget meget lang beskrivelse, der fortaeller den var en bitch uden at sige det direkte.");
//		
//		referencesCollection = projDao.getEmptyForeignCollection("referenced_person");
//		
//		pers = new ReferencedPerson();
//		pers.setFirstName("Jan");
//		pers.setLastName("Schoubo");
//		pers.setPostal(8600);
//		pers.setCity("Skanderborg");
//		pers.setEmail("jan@schoubo.dk");
//		pers.setPhone("22668899");
//		referencesCollection.add(pers);
//		
//		pers = new ReferencedPerson();
//		pers.setFirstName("Kirsten");
//		pers.setLastName("Schoubo");
//		pers.setPostal(8600);
//		pers.setCity("Skanderborg");
//		pers.setEmail("kirsten@schoubo.dk");
//		pers.setPhone("33221144");
//		referencesCollection.add(pers);
//		
//		proj.setReferences(referencesCollection);
//		
//		skillCollection = projDao.getEmptyForeignCollection("skills");
//		
//		
//		proj.setSkills(skillCollection);
//		
//		proj.setTeamWork(TeamWork.halfhalf);
//		projDao.create(proj);
//		
//		proj = new Project();
//		proj.setName("Writing phonegap plugins");
//		proj.setShortDecription("Foredrag i Amsterdam");
//		proj.setFullDescription("Saadan laver man plugins til PhoneGap. Tag en hammer, slaa Peter, skrig av!");
//		referencesCollection = projDao.getEmptyForeignCollection("referenced_person");
//		proj.setReferences(referencesCollection);
//		skillCollection = projDao.getEmptyForeignCollection("skills");
//		proj.setSkills(skillCollection);
//		proj.setTeamWork(TeamWork.solo);
//		projDao.create(proj);
//		
//		proj = new Project();
//		proj.setName("Hjaelperadministrationen");
//		proj.setShortDecription("Man skal huske\"");
//		proj.setFullDescription("Handicappede lider, hvor er det synd. BUF!");
//		referencesCollection = projDao.getEmptyForeignCollection("referenced_person");
//		proj.setReferences(referencesCollection);
//		skillCollection = projDao.getEmptyForeignCollection("skills");
//		proj.setSkills(skillCollection);
//		proj.setTeamWork(TeamWork.team);
//		projDao.create(proj);
//		
//		Log.i(DataHelper.class.getName(),
//				"created new entries in onCreate");
	}
}

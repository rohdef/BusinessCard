package dk.rohdef.rohdeBusinessCard;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import dk.rohdef.rohdeBusinessCard.model.Person;
import dk.rohdef.rohdeBusinessCard.model.Project;
import dk.rohdef.rohdeBusinessCard.model.ReferencedPerson;
import dk.rohdef.rohdeBusinessCard.model.Skill;
import dk.rohdef.rohdeBusinessCard.model.TeamWork;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	// name of the database file for your application -- change to something
	// appropriate for your app
	private static final String DATABASE_NAME = "businessCard.db";
	// any time you make changes to your database objects, you may have to
	// increase the database version
	private static final int DATABASE_VERSION = 1;

	// the DAO object we use to access the SimpleData table
	private Dao<Person, String> personDao = null;
	private RuntimeExceptionDao<Person, String> personRuntimeDao = null;
	private Dao<Project, String> projectDao = null;
	private RuntimeExceptionDao<Project, Integer> projectRuntimeDao = null;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * This is called when the database is first created. Usually you should
	 * call createTable statements here to create the tables that will store
	 * your data.
	 */
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onCreate");
			TableUtils.createTable(connectionSource, Person.class);
			TableUtils.createTable(connectionSource, Skill.class);
			TableUtils.createTable(connectionSource, ReferencedPerson.class);
			TableUtils.createTable(connectionSource, Project.class);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}

		// here we try inserting data in the on-create as a test
		RuntimeExceptionDao<Person, String> persDao = getPersonDao();
		RuntimeExceptionDao<Project, Integer> projDao = getProjectDao();
		
		Person person = new Person();
		person.setFirstName("Rohde");
		person.setLastName("Fischer");
		person.setAddress("Kirkegårdsvej 10 D, 3.-3");
		person.setPostal(8000);
		person.setCity("Aarhus");
		person.setEmail("rohdef@rohdef.dk");
		person.setPhone("21680621");
		
		persDao.create(person);
		
		ReferencedPerson pers;
		Project proj;
		ForeignCollection<ReferencedPerson> referencesCollection;
		ForeignCollection<Skill> skillCollection;

		proj = new Project();
		proj.setName("Klubhaeftet");
		proj.setShortDecription("En mobil udgave af Klubhaeftet");
		proj.setFullDescription("Meget meget lang beskrivelse, der fortaeller den var en bitch uden at sige det direkte.");
		
		referencesCollection = projDao.getEmptyForeignCollection("referenced_person");
		
		pers = new ReferencedPerson();
		pers.setFirstName("Jan");
		pers.setLastName("Schoubo");
		pers.setPostal(8600);
		pers.setCity("Skanderborg");
		pers.setEmail("jan@schoubo.dk");
		pers.setPhone("22668899");
		referencesCollection.add(pers);
		
		pers = new ReferencedPerson();
		pers.setFirstName("Kirsten");
		pers.setLastName("Schoubo");
		pers.setPostal(8600);
		pers.setCity("Skanderborg");
		pers.setEmail("kirsten@schoubo.dk");
		pers.setPhone("33221144");
		referencesCollection.add(pers);
		
		proj.setReferences(referencesCollection);
		
		skillCollection = projDao.getEmptyForeignCollection("skills");
		
		
		
		proj.setSkills(skillCollection);
		
		proj.setTeamWork(TeamWork.halfhalf);
		projDao.create(proj);
		
		proj = new Project();
		proj.setName("Writing phonegap plugins");
		proj.setShortDecription("Foredrag i Amsterdam");
		proj.setFullDescription("Saadan laver man plugins til PhoneGap. Tag en hammer, slaa Peter, skrig av!");
		referencesCollection = projDao.getEmptyForeignCollection("referenced_person");
		proj.setReferences(referencesCollection);
		skillCollection = projDao.getEmptyForeignCollection("skills");
		proj.setSkills(skillCollection);
		proj.setTeamWork(TeamWork.solo);
		projDao.create(proj);
		
		proj = new Project();
		proj.setName("Hjaelperadministrationen");
		proj.setShortDecription("Man skal huske\"");
		proj.setFullDescription("Handicappede lider, hvor er det synd. BUF!");
		referencesCollection = projDao.getEmptyForeignCollection("referenced_person");
		proj.setReferences(referencesCollection);
		skillCollection = projDao.getEmptyForeignCollection("skills");
		proj.setSkills(skillCollection);
		proj.setTeamWork(TeamWork.team);
		projDao.create(proj);
		
		Log.i(DatabaseHelper.class.getName(),
				"created new entries in onCreate");
	}

	/**
	 * This is called when your application is upgraded and it has a higher
	 * version number. This allows you to adjust the various data to match the
	 * new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onUpgrade");
			TableUtils.dropTable(connectionSource, Person.class, true);
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns the Database Access Object (DAO) for our Person class. It
	 * will create it or just give the cached value.
	 * @throws java.sql.SQLException 
	 */
	public Dao<Person, String> getPersonDaoSimple() throws SQLException {
		if (personDao == null) {
			personDao = getDao(Person.class);
		}
		return personDao;
	}

	/**
	 * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao
	 * for our Person class. It will create it or just give the cached
	 * value. RuntimeExceptionDao only through RuntimeExceptions.
	 */
	public RuntimeExceptionDao<Person, String> getPersonDao() {
		if (personRuntimeDao == null) {
			personRuntimeDao = getRuntimeExceptionDao(Person.class);
		}
		return personRuntimeDao;
	}
	
	/**
	 * Returns the Database Access Object (DAO) for our Person class. It
	 * will create it or just give the cached value.
	 * @throws java.sql.SQLException 
	 */
	public Dao<Project, String> getProjectDaoSimple() throws SQLException {
		if (projectDao == null) {
			projectDao = getDao(Project.class);
		}
		return projectDao;
	}
	
	/**
	 * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao
	 * for our Person class. It will create it or just give the cached
	 * value. RuntimeExceptionDao only through RuntimeExceptions.
	 */
	public RuntimeExceptionDao<Project, Integer> getProjectDao() {
		if (projectRuntimeDao == null) {
			projectRuntimeDao = getRuntimeExceptionDao(Project.class);
		}
		return projectRuntimeDao;
	}

	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
		personRuntimeDao = null;
	}
}

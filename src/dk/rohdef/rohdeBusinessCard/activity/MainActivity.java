package dk.rohdef.rohdeBusinessCard.activity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import dk.rohdef.rohdeBusinessCard.DataHelper;
import dk.rohdef.rohdeBusinessCard.R;

public class MainActivity extends Activity {
	private static final String FIRST_RUN = "firstRun";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SharedPreferences firstRun = getSharedPreferences(FIRST_RUN, 0);
		boolean firstTimeRunning = firstRun.getBoolean(FIRST_RUN, true);
		if (firstTimeRunning) {
			initialize();
			SharedPreferences.Editor editor = firstRun.edit();
			editor.putBoolean(FIRST_RUN, false);
			editor.commit();
		}
		
		new DataHelper(this).getContactDetails();
		
		setContentView(R.layout.activity_main);
	}
	
	private void initialize() {
		int i;
		byte[] readData = new byte[1024*500];
		FileOutputStream fos;
		InputStream fis;
		
		HashMap<String, Integer> resources = new HashMap<String, Integer>();
		resources.put("contact.json", R.raw.contact);
		resources.put("projects.json", R.raw.projects);
		resources.put("skills.json", R.raw.skills);
		resources.put("references.json", R.raw.references);
		
		for (Map.Entry<String, Integer> entry : resources.entrySet()) {
			try {
				fos = openFileOutput(entry.getKey(), Context.MODE_PRIVATE);
				fis = getResources().openRawResource(entry.getValue());
				
				IOUtils.copy(fis, fos);
				
				fos.close();
				fis.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
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

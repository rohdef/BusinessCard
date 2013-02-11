package dk.rohdef.rohdeBusinessCard.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import dk.rohdef.rohdeBusinessCard.R;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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

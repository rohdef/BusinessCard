package dk.rohdef.rohdeBusinessCard.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ExpandableListView;
import dk.rohdef.rohdeBusinessCard.DataHelper;
import dk.rohdef.rohdeBusinessCard.ProjectsAdapter;
import dk.rohdef.rohdeBusinessCard.R;
import dk.rohdef.rohdeBusinessCard.model.Project;

public class Projects extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.projects);
		
		List<Project> projects = new DataHelper().getProjects();

		ProjectsAdapter adapter = new ProjectsAdapter(this, projects);
		ExpandableListView list = (ExpandableListView) findViewById(R.id.expandableListView1);
		list.setAdapter(adapter);
		
		Display newDisplay = getWindowManager().getDefaultDisplay(); 
		int width = newDisplay.getWidth();
		list.setIndicatorBounds(width-getDipsFromPixel(35), width-getDipsFromPixel(5));
	}

	public int getDipsFromPixel(float pixels) {
		// Get the screen's density scale
		final float scale = getResources().getDisplayMetrics().density;
		// Convert the dps to pixels, based on density scale
		return (int) (pixels * scale + 0.5f);
	}
}

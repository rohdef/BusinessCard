package dk.rohdef.rohdeBusinessCard.activity;

import java.util.List;

import android.os.Bundle;
import android.view.Display;
import android.widget.ExpandableListView;
import dk.rohdef.rohdeBusinessCard.DataHelper;
import dk.rohdef.rohdeBusinessCard.Helpers;
import dk.rohdef.rohdeBusinessCard.ProjectsAdapter;
import dk.rohdef.rohdeBusinessCard.R;
import dk.rohdef.rohdeBusinessCard.model.Project;

public class Projects extends TrackedActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.projects);
		
		List<Project> projects = DataHelper.getInstance().getProjects();

		ProjectsAdapter adapter = new ProjectsAdapter(this, projects);
		ExpandableListView list = (ExpandableListView) findViewById(R.id.projectsListView);
		list.setAdapter(adapter);
		
		Display newDisplay = getWindowManager().getDefaultDisplay(); 
		int width = newDisplay.getWidth();
		list.setIndicatorBounds(width-Helpers.getDipsFromPixel(35, getResources()),
				width-Helpers.getDipsFromPixel(5, getResources()));
	}
}

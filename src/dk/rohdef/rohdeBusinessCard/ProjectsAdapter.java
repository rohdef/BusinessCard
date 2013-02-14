package dk.rohdef.rohdeBusinessCard;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import dk.rohdef.rohdeBusinessCard.model.Project;

public class ProjectsAdapter extends BaseExpandableListAdapter {
	private List<Project> projects;
	private LayoutInflater inflater;
	private Context context;
	
	public ProjectsAdapter(Context context, List<Project> projects) {
		super();
		
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
		
		this.projects = projects;
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return projects.get(groupPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return groupPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		Project project = (Project) getChild(groupPosition, childPosition);
		
		if (convertView == null)
			convertView = inflater.inflate(R.layout.project_view_details, null);
		
		TextView detailsText = (TextView) convertView.findViewById(R.id.projectDetailsText);
		detailsText.setText(project.getFullDescription());
		
		LinearLayout skillsLayout = (LinearLayout) convertView.findViewById(R.id.skillsLayout);
		LinearLayout.LayoutParams buttonLayout = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		
		Button skillButton;
		
		for (String skill : project.getSkills()) {
			skillButton = new Button(this.context);
			skillButton.setLayoutParams(buttonLayout);
			skillButton.setBackgroundResource(R.drawable.technical_button);
			skillButton.setText(skill);
			
			skillsLayout.addView(skillButton);
		}
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		System.out.println("Get group: " + groupPosition);
		return projects.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return projects.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		Project project = (Project) getGroup(groupPosition);
		
		if (convertView == null)
			convertView = inflater.inflate(R.layout.project_view_short, null);
		
		TextView headingTextView = (TextView) convertView.findViewById(R.id.projectDetailsHeading);
		headingTextView.setText(project.getName());
		
		TextView bylineTextView = (TextView) convertView.findViewById(R.id.projectDetailsByline);
		bylineTextView.setText(project.getShortDescription());
		
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
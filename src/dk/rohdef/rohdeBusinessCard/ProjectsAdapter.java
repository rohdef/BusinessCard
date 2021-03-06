package dk.rohdef.rohdeBusinessCard;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import dk.rohdef.rohdeBusinessCard.layout.FlowLayout;
import dk.rohdef.rohdeBusinessCard.model.Project;
import dk.rohdef.rohdeBusinessCard.model.Skill;

public class ProjectsAdapter extends BaseExpandableListAdapter {
	private List<Project> projects;
	private LayoutInflater inflater;
	private Context context;
	
	public ProjectsAdapter(Context context, List<Project> projects) {
		super();
		this.context = context;
		this.projects = projects;
		
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return getGroup(groupPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return groupPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		Project project = (Project) getChild(groupPosition, childPosition);
		convertView = inflater.inflate(R.layout.project_view_details, null);
		
		if (groupPosition%2 == 0)
			convertView.setBackgroundColor(Color.LTGRAY);
		
		TextView detailsText = (TextView) convertView.findViewById(R.id.projectDetailsText);
		detailsText.setText(project.getFullDescription());
		
		FlowLayout skillsLayout = (FlowLayout) convertView.findViewById(R.id.skillListLayout);
		FlowLayout.LayoutParams buttonLayout = new FlowLayout.LayoutParams(
				FlowLayout.LayoutParams.WRAP_CONTENT,
				FlowLayout.LayoutParams.WRAP_CONTENT);
		
		Button skillButton;
		Skill realSkill;;
		Map<String, Skill> skillsMap = DataHelper.getInstance().getSkillsMap();
		
		for (String skill : project.getSkills()) {
			realSkill = skillsMap.get(skill); 
			
			skillButton = new Button(this.context);
			skillButton.setLayoutParams(buttonLayout);
			skillButton.setBackgroundResource(R.drawable.technical_button);
			
			int color = Helpers.getSkillColor(context, realSkill.getSkillType());
			
			skillButton.getBackground().setColorFilter(color, Mode.MULTIPLY);
			skillButton.setText(realSkill.getName());
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
		convertView = inflater.inflate(R.layout.project_view_short, null);
		
		if (groupPosition%2 == 0)
			convertView.setBackgroundColor(Color.LTGRAY);
		
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
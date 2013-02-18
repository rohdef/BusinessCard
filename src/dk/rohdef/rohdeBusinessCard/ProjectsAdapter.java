package dk.rohdef.rohdeBusinessCard;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import dk.rohdef.rohdeBusinessCard.model.Project;
import dk.rohdef.rohdeBusinessCard.model.Skill;
import dk.rohdef.rohdeBusinessCard.model.SkillType;

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
		convertView = inflater.inflate(R.layout.project_view_details, null);
		
		if (groupPosition%2 == 0)
			convertView.setBackgroundColor(Color.LTGRAY);
		
		TextView detailsText = (TextView) convertView.findViewById(R.id.projectDetailsText);
		detailsText.setText(project.getFullDescription());
		
		LinearLayout skillsLayout = (LinearLayout) convertView.findViewById(R.id.skillsLayout);
		LinearLayout.LayoutParams buttonLayout = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		
		Button skillButton;
		Skill realSkill;;
		Map<String, Skill> skillsMap = DataHelper.getInstance().getSkillsMap();
		
		for (String skill : project.getSkills()) {
			realSkill = skillsMap.get(skill); 
			
			skillButton = new Button(this.context);
			skillButton.setLayoutParams(buttonLayout);
			skillButton.setBackgroundResource(R.drawable.technical_button);
			
			Resources resources = context.getResources();
			int color;
			
			SkillType skillType = realSkill.getSkillType();
			if (skillType == SkillType.methodology) {
				color = resources.getColor(R.color.skill_methodology);
			} else if (skillType == SkillType.technical) {
				color = resources.getColor(R.color.skill_technical);
			} else if (skillType == SkillType.tool) {
				color = resources.getColor(R.color.skill_tool);
			} else if (skillType == SkillType.personal) {
				color = resources.getColor(R.color.skill_personal);
			} else {
				color = Color.WHITE;
			}
			
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
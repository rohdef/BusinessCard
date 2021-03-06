package dk.rohdef.rohdeBusinessCard;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import dk.rohdef.rohdeBusinessCard.layout.FlowLayout;
import dk.rohdef.rohdeBusinessCard.model.Project;
import dk.rohdef.rohdeBusinessCard.model.Skill;

public class SkillsAdapter extends BaseExpandableListAdapter {
	private List<Skill> skills;
	private LayoutInflater inflater;
	private Context context;
	
	public SkillsAdapter(Context context, List<Skill> skills) {
		super();
		this.skills = skills;
		this.context = context;
		
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getGroupCount() {
		return skills.size();
	}

	@Override
	public Object getGroup(int position) {
		return skills.get(position);
	}

	@Override
	public long getGroupId(int position) {
		return position;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		
		Skill skill = (Skill) getGroup(groupPosition);
		convertView = inflater.inflate(R.layout.skill_view_short, null);
		
		if (groupPosition%2 == 0)
			convertView.setBackgroundColor(Color.LTGRAY);
		
		TextView skillName = (TextView) convertView.findViewById(R.id.skill_name);
		skillName.setText(skill.getName());
		
		int color = Helpers.getSkillColor(context, skill.getSkillType());
		Drawable[] compoundDrawables = skillName.getCompoundDrawables();
		for (Drawable compound : compoundDrawables) {
			if (compound != null)
				compound.setColorFilter(color, Mode.MULTIPLY);
		}
		
		return convertView;
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
		Skill skill = (Skill) getChild(groupPosition, childPosition);
		
		convertView = inflater.inflate(R.layout.skill_view_details, null);

		if (groupPosition%2 == 0)
			convertView.setBackgroundColor(Color.LTGRAY);
		
		TextView skillDescription = (TextView) convertView.findViewById(R.id.skillDetails);
		skillDescription.setText(skill.getDescription());
		
		FlowLayout projectsLayout = (FlowLayout) convertView.findViewById(R.id.projectListLayout);
		FlowLayout.LayoutParams buttonLayout = new FlowLayout.LayoutParams(
				FlowLayout.LayoutParams.WRAP_CONTENT,
				FlowLayout.LayoutParams.WRAP_CONTENT);
		
		Button projectButton;
		Project realProject;
		Map<String, Project> projectsMap = DataHelper.getInstance().getProjectsMap();
		
		for (String project : skill.getProjects()) {
			realProject = projectsMap.get(project); 
			
			projectButton = new Button(this.context);
			projectButton.setLayoutParams(buttonLayout);
			projectButton.setBackgroundResource(R.drawable.technical_button);
			
			projectButton.setText(realProject.getName());
			projectsLayout.addView(projectButton);
		}
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}
}

package dk.rohdef.rohdeBusinessCard;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import dk.rohdef.rohdeBusinessCard.model.Skill;

public class SkillsAdapter extends BaseAdapter {
	private List<Skill> skills;
	private LayoutInflater inflater;
//	private Context context;
	
	public SkillsAdapter(Context context, List<Skill> skills) {
		super();
		this.skills = skills;
//		this.context = context;
		
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return skills.size();
	}

	@Override
	public Object getItem(int position) {
		return skills.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Skill skill = (Skill) getItem(position);
		convertView = inflater.inflate(R.layout.skill_list_view, null);
		
		if (position%2 == 0)
			convertView.setBackgroundColor(Color.LTGRAY);
		
		TextView skillName = (TextView) convertView.findViewById(R.id.skill_name);
		skillName.setText(skill.getName());
		
		return convertView;
	}
}

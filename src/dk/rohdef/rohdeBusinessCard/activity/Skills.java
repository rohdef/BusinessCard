package dk.rohdef.rohdeBusinessCard.activity;

import java.util.List;

import android.os.Bundle;
import android.view.Display;
import android.widget.ExpandableListView;
import dk.rohdef.rohdeBusinessCard.DataHelper;
import dk.rohdef.rohdeBusinessCard.Helpers;
import dk.rohdef.rohdeBusinessCard.R;
import dk.rohdef.rohdeBusinessCard.SkillsAdapter;
import dk.rohdef.rohdeBusinessCard.model.Skill;

public class Skills extends TrackedActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.skills);
		
		List<Skill> skills = DataHelper.getInstance().getSkills();
		
		SkillsAdapter adapter = new SkillsAdapter(this, skills);
		ExpandableListView skillsList = (ExpandableListView) findViewById(R.id.skillsList);
		skillsList.setAdapter(adapter);
		
		Display newDisplay = getWindowManager().getDefaultDisplay(); 
		int width = newDisplay.getWidth();
		skillsList.setIndicatorBounds(width-Helpers.getDipsFromPixel(35, getResources()),
				width-Helpers.getDipsFromPixel(5, getResources()));
	}
}
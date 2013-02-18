package dk.rohdef.rohdeBusinessCard.activity;

import java.util.List;

import dk.rohdef.rohdeBusinessCard.DataHelper;
import dk.rohdef.rohdeBusinessCard.R;
import dk.rohdef.rohdeBusinessCard.SkillsAdapter;
import dk.rohdef.rohdeBusinessCard.model.Skill;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class Skills extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.skills);
		
		List<Skill> skills = DataHelper.getInstance().getSkills();
		
		SkillsAdapter adapter = new SkillsAdapter(this, skills);
		ListView skillsList = (ListView) findViewById(R.id.skillsList);
		skillsList.setAdapter(adapter);
	}
}
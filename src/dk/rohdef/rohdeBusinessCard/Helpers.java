package dk.rohdef.rohdeBusinessCard;

import dk.rohdef.rohdeBusinessCard.model.SkillType;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;

public class Helpers {
	private Helpers() {
	}
	
	public static int getDipsFromPixel(float pixels, Resources resources) {
		// Get the screen's density scale
		final float scale = resources.getDisplayMetrics().density;
		// Convert the dps to pixels, based on density scale
		return (int) (pixels * scale + 0.5f);
	}
	
	public static int getSkillColor(Context context, SkillType skillType) {
		Resources resources = context.getResources();
		int color;
		
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
		
		return color;
	}
}

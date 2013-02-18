package dk.rohdef.rohdeBusinessCard;

import android.content.res.Resources;

public class Helpers {
	private Helpers() {
	}
	
	public static int getDipsFromPixel(float pixels, Resources resources) {
		// Get the screen's density scale
		final float scale = resources.getDisplayMetrics().density;
		// Convert the dps to pixels, based on density scale
		return (int) (pixels * scale + 0.5f);
	}
}

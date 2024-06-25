package com.texnologia_logismikou.Cinematrix.Managers;

//import com.texnologia_logismikou.Cinematrix.CinemaSystem;
import com.texnologia_logismikou.Cinematrix.Views.NearCinemasView;
import com.texnologia_logismikou.Cinematrix.Views.View;

public class AvailableCinemasDisplay extends DisplayManager
{
	@Override
	public void refresh() {
		View view = new NearCinemasView();
				
		view.prepare();
		getController().setContent(view.getParent());
	}
}

package com.texnologia_logismikou.Cinematrix.Managers;

import com.texnologia_logismikou.Cinematrix.Views.NearCinemasView;
import com.texnologia_logismikou.Cinematrix.Views.View;

public class AvailableCinemasDisplay extends DisplayManager
{
	@Override
	public void refresh() {
		NearCinemasView view = new NearCinemasView();
				
		try {
			view.prepare();
			getController().setContent(view.getParent());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

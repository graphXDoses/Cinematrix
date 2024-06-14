package com.texnologia_logismikou.Cinematrix;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LogoButtonController {
	
	private LicensePopup popup;
	
	@FXML
	void initialize()
	{
		popup = LicensePopup.getInstance();
		popup.getDialogPane().setMinWidth(450);
		popup.getDialogPane().setMinHeight(450);
		popup.getDialogPane().setMaxWidth(450);
		popup.getDialogPane().setMaxHeight(450);
	}

    @FXML
    void onClick(ActionEvent event) {
    	
    	popup.showAndWait();
    }

}

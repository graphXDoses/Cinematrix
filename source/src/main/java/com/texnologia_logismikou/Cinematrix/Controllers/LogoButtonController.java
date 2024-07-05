package com.texnologia_logismikou.Cinematrix.Controllers;

import com.texnologia_logismikou.Cinematrix.Managers.LicensePopup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class LogoButtonController {
	
	private Dialog<ButtonType> popup;
	
	@FXML
	void initialize()
	{
		popup = LicensePopup.getInstance().getDialog();
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

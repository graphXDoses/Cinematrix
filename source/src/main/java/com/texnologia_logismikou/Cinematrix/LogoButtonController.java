package com.texnologia_logismikou.Cinematrix;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LogoButtonController {

    @FXML
    void onClick(ActionEvent event) {
    	LicensePopup popup = LicensePopup.getInstance();
    	popup.showAndWait();
    }

}

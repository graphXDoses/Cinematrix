package com.texnologia_logismikou.Cinematrix;

import java.io.IOException;

import javafx.fxml.FXML;

public class ModalController {
	@FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}

package com.texnologia_logismikou.Cinematrix.Controllers;

import com.texnologia_logismikou.Cinematrix.Managers.ContextButtonContainer;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class MainUIController {

    @FXML
    private HBox header_bar;

    @FXML
    void initialize() {
        assert header_bar != null : "fx:id=\"header_bar\" was not injected: check your FXML file 'Main.fxml'.";
//        System.out.println(new ContextButtonContainer().getParent());
        header_bar.getChildren().add(2, new ContextButtonContainer().getParent());
    }

}

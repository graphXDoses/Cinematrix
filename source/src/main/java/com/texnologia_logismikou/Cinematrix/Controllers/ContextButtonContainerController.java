package com.texnologia_logismikou.Cinematrix.Controllers;

import com.texnologia_logismikou.Cinematrix.Managers.ContextButton;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class ContextButtonContainerController {

    @FXML
    private HBox ctx_btn_container;
    
    @FXML
    void initialize()
    {
    	assert ctx_btn_container != null : "fx:id \'ctx_btn_container\' was not injected";
    }
    
    public void addContextButton(ContextButton button)
    {
    	ctx_btn_container.getChildren().add(button.getParent());
    }

}

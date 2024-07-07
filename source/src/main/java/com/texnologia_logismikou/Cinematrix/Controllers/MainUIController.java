package com.texnologia_logismikou.Cinematrix.Controllers;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.Context;
import com.texnologia_logismikou.Cinematrix.Managers.ContextButtonContainer;
import com.texnologia_logismikou.Cinematrix.Managers.MainDisplay;
import com.texnologia_logismikou.Cinematrix.Managers.FooterBar;
import com.texnologia_logismikou.Cinematrix.Managers.LogoButton;
import com.texnologia_logismikou.Cinematrix.Managers.MovieModal;
import com.texnologia_logismikou.Cinematrix.Views.AllMoviesView;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MainUIController {

    @FXML private HBox header_bar;
    @FXML private BorderPane root;
//    private MainDisplay mainDisplay = new MainDisplay();

    @FXML
    void initialize() {
        assert header_bar != null : "fx:id=\"header_bar\" was not injected: check your FXML file 'Main.fxml'.";

        header_bar.getChildren().add(0, new LogoButton().getParent());
        header_bar.getChildren().add(2, new ContextButtonContainer().getParent());
        
        Context movieContext = CinematrixAPI.getInstance().getContexts().get(0);
        CinematrixAPI.getInstance().setActiveContext(movieContext);
        
        root.setCenter(CinematrixAPI.getInstance().getMainDisplay().getParent());
        root.setBottom(new FooterBar().getParent());
    }

}

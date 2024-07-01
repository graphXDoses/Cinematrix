package com.texnologia_logismikou.Cinematrix.Controllers;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.Contexts.Context;
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
    
    public void placeOnHeaderBar(LogoButton logoBtn)
    {
    	header_bar.getChildren().add(0, logoBtn.getParent());    	
    }
    
    public void placeOnHeaderBar(ContextButtonContainer container)
    {
    	header_bar.getChildren().add(2, container.getParent());    	
    }
    
    public void placeMainDisplay(MainDisplay main)
    {
    	CinematrixAPI.getInstance()
					 .setActiveContext(CinematrixAPI.MOVIE_CONTEXT);
    	root.setCenter(main.getParent());
    }
    
    public void placeFooterBar(FooterBar footer)
    {
    	root.setBottom(footer.getParent());    	
    }

}

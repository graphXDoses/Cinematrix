package com.texnologia_logismikou.Cinematrix.Controllers;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.Managers.ContextButtonContainer;
import com.texnologia_logismikou.Cinematrix.Managers.MainDisplay;
import com.texnologia_logismikou.Cinematrix.Managers.SearchBarWidget;
import com.texnologia_logismikou.Cinematrix.Managers.FooterBar;
import com.texnologia_logismikou.Cinematrix.Managers.LogoButton;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MainUIController {

    @FXML private HBox header_bar;
    @FXML private BorderPane root;

    @FXML private HBox logo_button_container;
    @FXML private HBox searchbar_container;
    @FXML private HBox context_button_container;
    
    public void placeOnHeaderBar(LogoButton logoBtn)
    {
    	logo_button_container.getChildren().add(logoBtn.getParent());    	
    }
    
    public void placeOnHeaderBar(SearchBarWidget bar)
    {
    	searchbar_container.getChildren().clear();
    	searchbar_container.getChildren().add(bar.getParent());
    }
    
    public void placeOnHeaderBar(ContextButtonContainer container)
    {
    	context_button_container.getChildren().add(container.getParent());    	
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

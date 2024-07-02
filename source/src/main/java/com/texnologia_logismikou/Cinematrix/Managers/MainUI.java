package com.texnologia_logismikou.Cinematrix.Managers;

import com.texnologia_logismikou.Cinematrix.App;
import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.Contexts.AccountContext;
import com.texnologia_logismikou.Cinematrix.Controllers.MainUIController;

import javafx.scene.Scene;

public class MainUI extends Manager<MainUIController>
{
	private static Scene thisScene;
	
	public static final LogoButton LOGO_BUTTON = new LogoButton();
	public static final SearchBarWidget SEARCHBAR_WIDGET = new SearchBarWidget();
	public static final ContextButtonContainer CONTEXT_BUTTON_CONTAINER = new ContextButtonContainer();
	public static final MainDisplay MAIN_DISPLAY = new MainDisplay();
	public static final FooterBar FOOTERBAR = new FooterBar();
	
	public MainUI()
	{
		super.loadFXML("Main");
		thisScene = new Scene(getParent());
		thisScene.getStylesheets().add(App.class.getResource("styles/styles.css").toExternalForm());
		
		getController().placeOnHeaderBar(LOGO_BUTTON);
		getController().placeOnHeaderBar(SEARCHBAR_WIDGET);
		getController().placeOnHeaderBar(CONTEXT_BUTTON_CONTAINER);
		getController().placeMainDisplay(MAIN_DISPLAY);
		getController().placeFooterBar(FOOTERBAR);
		
		FOOTERBAR.updateUserTypeDisplay();
	}
	
	public static Scene getScene()
	{
		return(thisScene);
	}
	
	public void reemplaceSearchBar()
	{
		if(CinematrixAPI.getInstance().getActiveContext() instanceof AccountContext)
			SEARCHBAR_WIDGET.setVisible(false);
		else
		{
			String ph = "Search for a ";
			ph +=  CinematrixAPI.getInstance().getActiveContext() == CinematrixAPI.MOVIE_CONTEXT ? "Movie" : "Cinema";
			SEARCHBAR_WIDGET.setPlaceholderText(ph);
			SEARCHBAR_WIDGET.setVisible(true);
		}
	}
}

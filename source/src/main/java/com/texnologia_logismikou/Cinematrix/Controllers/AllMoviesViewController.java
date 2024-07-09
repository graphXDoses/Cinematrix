package com.texnologia_logismikou.Cinematrix.Controllers;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.Managers.MovieModal;
import com.texnologia_logismikou.Cinematrix.Users.Admin;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AllMoviesViewController{

	@FXML private Pane root;
	
	@FXML private VBox main_vbox_container;
    
	@FXML private HBox add_new_movie_container;
    @FXML private VBox admin_vbox_container;
	
	@FXML private ScrollPane now_featuring_carousel;
	@FXML private HBox now_featuring_container;
	@FXML private Button now_featuring_carousel_end_button;
	@FXML private Button now_featuring_carousel_home_button;
    
    @FXML private ScrollPane upcomming_carousel;
    @FXML private HBox upcomming_container;
    @FXML private Button upcomming_carousel_home_button;
    @FXML private Button upcomming_carousel_end_button;
    
    private double padding = 60f;
	
	@FXML void initialize()
	{
		add_new_movie_container.getChildren().add(new MovieModal(null).getParent());
		((VBox)admin_vbox_container.getParent()).getChildren().remove(admin_vbox_container);
		
		root.widthProperty().addListener(new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	            if (newValue.floatValue()!=oldValue.floatValue())
	            {
	            	upcomming_carousel.setPrefWidth(((double) newValue)-padding);
	            	now_featuring_carousel.setPrefWidth(((double) newValue)-padding);
	            }
	        }
	    });
		
		now_featuring_container.getChildren().addListener(new InvalidationListener() {

		    @Override
		    public void invalidated(Observable o)
		    {
//		    	System.out.println(now_featuring_carousel.getWidth() + "<> " + now_featuring_container.getWidth());
		    	if(now_featuring_carousel.getWidth() > now_featuring_container.getWidth())
		    	{
		    		now_featuring_carousel_end_button.setVisible(false);
		    		now_featuring_carousel_home_button.setVisible(false);
//		    		System.out.println("YES");
		    	} else {
		    		now_featuring_carousel_end_button.setVisible(true);
		    		now_featuring_carousel_home_button.setVisible(false);
//		    		System.out.println("NO");
		    	}
		    	
		    	if(now_featuring_container.getChildren().size() < 5)
		    	{
		    		now_featuring_carousel_end_button.setVisible(false);
		    		now_featuring_carousel_home_button.setVisible(false);
		    	}
		    }

		});
		
		upcomming_container.getChildren().addListener(new InvalidationListener() {

		    @Override
		    public void invalidated(Observable o)
		    {
		    	if(upcomming_carousel.getWidth() > upcomming_container.getWidth())
		    	{
		    		upcomming_carousel_end_button.setVisible(false);
		    		upcomming_carousel_home_button.setVisible(false);
//		    		System.out.println("YES");
		    	} else {
		    		upcomming_carousel_end_button.setVisible(true);
		    		upcomming_carousel_home_button.setVisible(false);
//		    		System.out.println("NO");
		    	}
		    	if(now_featuring_container.getChildren().size() < 5)
		    	{
		    		now_featuring_carousel_end_button.setVisible(false);
		    		now_featuring_carousel_home_button.setVisible(false);
		    	}
		    }

		});
		
		now_featuring_carousel.hvalueProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		        if (newValue.floatValue()!= oldValue.floatValue())
		        {
		        	if(newValue.floatValue() == now_featuring_carousel.getHmax())
		        		now_featuring_carousel_end_button.setVisible(false);
		        	else if(newValue.floatValue() == now_featuring_carousel.getHmin())
		        		now_featuring_carousel_home_button.setVisible(false);
		        	else
		        	{
		        		now_featuring_carousel_end_button.setVisible(true);
		            	now_featuring_carousel_home_button.setVisible(true);
		        	}
		        }   	
		    }
		});
		
		upcomming_carousel.hvalueProperty().addListener(new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	            if (newValue.floatValue()!= oldValue.floatValue())
	            {
	            	if(newValue.floatValue() == upcomming_carousel.getHmax())
	            		upcomming_carousel_end_button.setVisible(false);
	            	else if(newValue.floatValue() == upcomming_carousel.getHmin())
	            		upcomming_carousel_home_button.setVisible(false);
	            	else
	            	{
	            		upcomming_carousel_end_button.setVisible(true);
		            	upcomming_carousel_home_button.setVisible(true);
	            	}
	            }   	
	        }
	    });
	}
	
	public void clearAll()
	{
		main_vbox_container.getChildren().remove(add_new_movie_container);
		now_featuring_container.getChildren().clear();
		upcomming_container.getChildren().clear();
	}
	
	public void appendNowFeaturing(MovieModal movie)
	{
		if(!now_featuring_container.getChildren().contains(movie.getParent()))
			now_featuring_container.getChildren().add(movie.getParent());
		now_featuring_carousel.setHvalue(0);
	}
	
	public void appendUpcomming(MovieModal movie)
	{
		if(!upcomming_container.getChildren().contains(movie.getParent()))
			upcomming_container.getChildren().add(movie.getParent());
		upcomming_carousel.setHvalue(0);
	}
	
	public void revealAdminSection()
	{
		if(CinematrixAPI.getInstance().getCurrentUser() instanceof Admin)
		{
			if(!main_vbox_container.getChildren().contains(admin_vbox_container))
				main_vbox_container.getChildren().add(0, admin_vbox_container);
		}
		else
			main_vbox_container.getChildren().remove(admin_vbox_container);
	}

}

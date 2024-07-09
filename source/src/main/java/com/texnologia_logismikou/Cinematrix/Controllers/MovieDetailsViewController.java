package com.texnologia_logismikou.Cinematrix.Controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.texnologia_logismikou.Cinematrix.Movie;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.MovieFields;
import com.texnologia_logismikou.Cinematrix.Managers.ScreeningDaySelectionButtonWidget;
import com.texnologia_logismikou.Cinematrix.Screening;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public class MovieDetailsViewController {

	@FXML private Label MPArating_label;
	@FXML private VBox cinema_display_container;
	@FXML private Label description_label;
	@FXML private Label director_label;
	@FXML private Label duration_label;
	@FXML private ImageView modal_cover;
	@FXML private Label modal_title_label;
	@FXML private Hyperlink moreDetailsLink;
	@FXML private VBox more_details_container;
	@FXML private VBox vbox_accordion_container;
	@FXML private WebView yt_trailer_player_area;
	@FXML private HBox like_hbox_container;
	@FXML private AnchorPane like_movie_container;

	@FXML private HBox days_available_container;

    @FXML private Button filter_all_button;
    @FXML private Button filter_dolby_button;
    @FXML private Button filter_standard_button;
	
	private static Image     blankCover;
	private static String    defaultText;
	
	private final String[]  detailsText    = {"MORE DETAILS", "LESS DETAILS"};
	private final int[]     modalMaxSize   = {430, 900};
	private int             expantionState = 0;

    @FXML
    void initialize() {
        assert modal_cover != null : "fx:id=\"modal_cover\" was not injected: check your FXML file 'MovieDetailsView.fxml'.";
        assert modal_title_label != null : "fx:id=\"modal_title_label\" was not injected: check your FXML file 'MovieDetailsView.fxml'.";
		
		blankCover  = modal_cover.getImage();
		defaultText =  modal_title_label.getText();
		
		vbox_accordion_container.setMaxHeight(modalMaxSize[0]);
		
		more_details_container.setVisible(false);
		more_details_container.getChildren().forEach(node->node.setVisible(true));
    }
    
	public void setMovieDetailData(Movie movie)
	{
    	
    	if(movie != null)
    	{    		
    		MovieFields fields = movie.getDoc().getFields();
    		
    		modal_cover.setImage(movie.getModal().getCoverImage());
    		modal_title_label.setText(fields.getTitle().getStringValue());
    		MPArating_label.setText(fields.getMpRating().getStringValue());
    		duration_label.setText(movie.getDuration());
    		yt_trailer_player_area.getEngine().load(fields.getYtTrailerUrl().getStringValue());
    		description_label.setText(fields.getDescription().getStringValue());
    		director_label.setText(fields.getDirector().getStringValue());
    	} else {
    		modal_cover.setImage(blankCover);
    		modal_title_label.setText(defaultText);
    		yt_trailer_player_area.getEngine().load(null);
    	}
	}
    
    public void setCinemaDisplay(Node cinemaDisplayRootNode)
    {
    	if(cinema_display_container.getChildren().isEmpty())
    		cinema_display_container.getChildren().add(cinemaDisplayRootNode);
    }
    
    public void setLikable(boolean value)
    {
    	if(value)
    	{
    		if(!like_hbox_container.getChildren().contains(like_movie_container))
				like_hbox_container.getChildren().add(0, like_movie_container);
    	} else {
    		if(like_movie_container.getParent() != null)
    			((HBox)like_movie_container.getParent()).getChildren().remove(like_movie_container);
    	}
    }
    
    @FXML
    void likeMovieCallback(ActionEvent event) {
		System.out.println((Node)event.getSource());
    }
    
    @FXML
    void toggleDetailsCallback(ActionEvent event) {
    	Hyperlink link = (Hyperlink)event.getSource();
    	expantionState = (expantionState + 1) % 2;
    	link.setText(detailsText[expantionState]);
    	vbox_accordion_container.setMaxHeight(modalMaxSize[expantionState]);
    	
    	more_details_container.setVisible(expantionState == 1);
    }
    
    @FXML
    void filterAllCallback(ActionEvent event) {

    }

    @FXML
    void filterDolbyCallback(ActionEvent event) {

    }

    @FXML
    void filterStandardCallback(ActionEvent event) {

    }

    // TODO: Work on this first thing in the morning!
	public List<ScreeningDaySelectionButtonWidget> setAvailableDays(List<Screening> associateScreenings)
	{
		List<LocalDateTime> uniHours = new ArrayList<>();
		associateScreenings.forEach(s->{
			s.getHours().forEach(h->{
				uniHours.add(h);
			});
		});
		
		Set<LocalDate> uniqueDays = uniHours.stream()
                .map(LocalDateTime::toLocalDate)
                .collect(Collectors.toCollection(HashSet::new));
		
		List<ScreeningDaySelectionButtonWidget> buttons = new ArrayList<ScreeningDaySelectionButtonWidget>();
		days_available_container.getChildren().clear();
		uniqueDays.stream().sorted().forEach(day->{
//			System.out.println(day.format(DateTimeFormatter.ofPattern("dd LLLL yyyy")));
			ScreeningDaySelectionButtonWidget button = new ScreeningDaySelectionButtonWidget(day);
			days_available_container.getChildren().add(button.getParent());
			buttons.add(button);
		});
		
		
		return(buttons);
		
		/*
		associateScreenings.stream().filter(screening->{
			List<LocalDateTime> dates = screening.getHours().stream().filter(hour->{
				hour.getDayOfMonth();
			});
			
			return(!dates.isEmpty());
		});
		days_available_container.getChildren().clear();
		associateScreenings.forEach(screening->{
			days_available_container.getChildren().add(screening.);
		});
		*/
	}

}

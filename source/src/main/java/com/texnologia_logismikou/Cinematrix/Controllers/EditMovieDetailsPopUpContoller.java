package com.texnologia_logismikou.Cinematrix.Controllers;

import java.util.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.texnologia_logismikou.Cinematrix.Movie;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.MovieFields;
import com.texnologia_logismikou.Cinematrix.Managers.CategoryBubbleWidget;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebView;

public class EditMovieDetailsPopUpContoller {

	@FXML private HBox category_container;
	@FXML private TextArea description_textarea;
	@FXML private TextField director_field;
	@FXML private TextField duration_field;
	@FXML private ImageView modal_cover;
	@FXML private VBox more_details_container;
	@FXML private ChoiceBox<String> rating_combo;
	@FXML private ScrollPane scroller;
	@FXML private TextField title_field;
	@FXML private TextField trailer_URL_field;
	@FXML private VBox vbox_accordion_container;
	@FXML private WebView yt_trailer_player_area;
	@FXML private Button add_category_button;

    @FXML
    void initialize()
    {    
        scroller.getStyleClass().clear();
		scroller.getStyleClass().add(".scroll-bar");
//		rating_combo.setButtonCell(new ListCell<String>().setText("R"));
		trailer_URL_field.textProperty().addListener((observable, oldValue, newValue) -> {
			if(!newValue.isEmpty())
			{
				if(newValue.contains("/embed/"))
					yt_trailer_player_area.getEngine().load(trailer_URL_field.getText());
			}
		});
		
		String[] mpa_ratings = {"NC-17", "R", "PG-13", "PG", "G"};
		
		rating_combo.getItems().addAll(mpa_ratings);
    }

	public void setData(Movie movie)
	{
		if(movie != null)
		{
			MovieFields fields = movie.getDoc().getFields();
			title_field.setText(fields.getTitle().getStringValue());
			duration_field.setText(fields.getDuration().getDoubleValue() + "");
			modal_cover.setImage(movie.getModal().getCoverImage());
			description_textarea.setText(fields.getDescription().getStringValue());
			director_field.setText(fields.getDirector().getStringValue());
			trailer_URL_field.setText(fields.getYtTrailerUrl().getStringValue());
			
			rating_combo.setValue(fields.getMpaRating().getStringValue());
			
			List<CategoryBubbleWidget> category_bubbles = new ArrayList<CategoryBubbleWidget>();
			
			Arrays.asList(fields.getCategories().getArrayValue().getValues()).forEach(cat_name->{
				category_bubbles.add(new CategoryBubbleWidget(cat_name.getStringValue()));
			});
			category_bubbles.forEach(bubble->{
				category_container.getChildren().add(bubble.getParent());
			});
		}
	}
    
}

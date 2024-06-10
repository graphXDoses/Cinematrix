package com.texnologia_logismikou.Cinematrix;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.VLineTo;

public class MovieModalController {

	@FXML
    private ImageView mdl_cover;
	
	public void setData(Movie movie)
	{
		Image img = new Image(getClass().getResource(movie.getUrl()).toExternalForm());
		mdl_cover.setImage(img);
		/*
		Rectangle clip = new Rectangle();
		clip.setWidth(220);
		clip.setHeight(300);
		clip.setArcHeight(20);
		clip.setArcWidth(20);
		
		mdl_cover.setClip(clip);
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);
		WritableImage image = mdl_cover.snapshot(params, null);
		
		mdl_cover.setClip(null);
		mdl_cover.setImage(image);
		mdl_cover.setFitWidth(220);
		mdl_cover.setFitHeight(300);
        mdl_cover.setClip(getClip(mdl_cover, .05, .05, .05, .05));
		 */
	}
	
	private Node getClip(ImageView imageView, double topLeft, double topRight, double bottomLeft, double bottomRight) {
        Path clip;

        double height = imageView.getFitHeight();
        double width = imageView.getFitWidth();
        double radius1 = height * topLeft;
        double radius2 = height * topRight;
        double radius3 = height * bottomLeft;
        double radius4 = height * bottomRight;

        clip = new Path(new MoveTo(0, radius1),
                new ArcTo(radius1, radius1, 0, radius1, 0, false, true),
                new HLineTo(width - radius2),
                new ArcTo(radius2, radius2, 0, width, radius2, false, true),
                new VLineTo(height - radius4),
                new ArcTo(radius4, radius4, 0, width - radius4, height, false, true),
                new HLineTo(radius3),
                new ArcTo(radius3, radius3, 0, 0, height - radius3, false, true));

        clip.setFill(Color.ALICEBLUE);

        return clip;

    }
	
}

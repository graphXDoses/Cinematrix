package com.texnologia_logismikou.Cinematrix.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CategoryBubbleWidgetController {

	@FXML
    private TextField category_field;
    
    public void setData(String cat_name)
    {
    	category_field.setText(cat_name);
    }

}

package com.texnologia_logismikou.Cinematrix.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;

public class VenueTopDownViewController {

    @FXML
    private GridPane seat_grid;
    
    private final int[][] grid =
	{
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0}
    };

    
    @FXML
    void initialize()
    {	
    	for (int i = 0; i < grid.length; i++)
    	{
            for (int j = 0; j < grid[i].length; j++)
            {
            	if(grid[j][i] == 1)
            	{
            		Button btn = new Button();
//            		btn.setPrefWidth(50);
//            		btn.setPrefHeight(50);
//            		btn.setTooltip(new Tooltip("Seat " + i*100 + j));
            		seat_grid.add(new Button(), i, j);
            	}
            }
        }
    }
}

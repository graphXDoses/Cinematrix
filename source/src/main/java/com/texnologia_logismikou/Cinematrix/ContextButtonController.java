package com.texnologia_logismikou.Cinematrix;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ContextButtonController {

	@FXML
	private Pane ctx_btn_root;

	@FXML
	private HBox ctx_btn_container;
	
	@FXML
	private Pane ctx_btn_flap_bg_l;
	
	@FXML
	private Pane ctx_btn_flap_l;
	
	@FXML
	private Button ctx_btn_button;
	
	@FXML
	private ImageView ctx_btn_ico;
	
	@FXML
	private Label ctx_btn_lbl;
	
	@FXML
	private Pane ctx_btn_flap_bg_r;
	
	@FXML
	private Pane ctx_btn_flap_r;
    
    private static Button activeButton = null;
    private static List<ContextButtonHandler> buttons = new ArrayList<>();
    
//  active_css
    private static String active_context_button_container = "active-context-button-container";
    private static String active_context_button_flap_bg = "active-context-button-flap-bg";
    private static String active_context_button_flap_l = "active-context-button-flap-l";
    private static String active_context_button = "active-context-button";
    private static String active_context_button_flap_r = "active-context-button-flap-r";

//  inactive_css
    private static String inactive_context_button_container = "inactive-context-button-container";
    private static String inactive_context_button_flap_bg = "inactive-context-button-flap-bg";
    private static String inactive_context_button_flap_l = "inactive-context-button-flap-l";
    private static String inactive_context_button = "inactive-context-button";
    private static String inactive_context_button_flap_r = "inactive-context-button-flap-r";
    
    @FXML
    void clickEventHandler(MouseEvent event) {
    	Button btn = (Button)(event.getSource());
    	
    	if(activeButton != btn)
		{
    		activeButton = btn;
    		
			for(ContextButtonHandler h: buttons)
			{
				if(h.checkSame(activeButton))
					h.activate();
				else
					h.deactivate();
			}
		}
    	
    	
    }
    
    public void setData(Context ctx)
	{
		Image img = new Image(getClass().getResource(ctx.getUrl()).toExternalForm());
		
		ctx_btn_ico.setImage(img);
		ctx_btn_lbl.setText(ctx.getName());
		
		ContextButtonHandler handler = new ContextButtonHandler();
		
		if(ctx.getName().equals("Movies"))
			handler.activate();
			
		buttons.add(handler);
	}
    
    class ContextButtonHandler
    {
    	List<Node>   nodes        = new ArrayList<>(Arrays.asList(ctx_btn_container, ctx_btn_flap_bg_l, ctx_btn_flap_l, ctx_btn_button, ctx_btn_flap_bg_r, ctx_btn_flap_r));
    	
    	public void activate()
    	{
    		nodes.get(0).getStyleClass().clear();
    		nodes.get(0).getStyleClass().add(active_context_button_container);

    		nodes.get(1).getStyleClass().clear();
    		nodes.get(1).getStyleClass().add(active_context_button_flap_bg);

    		nodes.get(2).getStyleClass().clear();
    		nodes.get(2).getStyleClass().add(active_context_button_flap_l);

    		nodes.get(3).getStyleClass().clear();
    		nodes.get(3).getStyleClass().add(active_context_button);

    		nodes.get(4).getStyleClass().clear();
    		nodes.get(4).getStyleClass().add(active_context_button_flap_bg);

    		nodes.get(5).getStyleClass().clear();
    		nodes.get(5).getStyleClass().add(active_context_button_flap_r);
    	}
    	
    	public void deactivate()
    	{
    		nodes.get(0).getStyleClass().clear();
    		nodes.get(0).getStyleClass().add(inactive_context_button_container);

    		nodes.get(1).getStyleClass().clear();
    		nodes.get(1).getStyleClass().add(inactive_context_button_flap_bg);

    		nodes.get(2).getStyleClass().clear();
    		nodes.get(2).getStyleClass().add(inactive_context_button_flap_l);

    		nodes.get(3).getStyleClass().clear();
    		nodes.get(3).getStyleClass().add(inactive_context_button);

    		nodes.get(4).getStyleClass().clear();
    		nodes.get(4).getStyleClass().add(inactive_context_button_flap_bg);

    		nodes.get(5).getStyleClass().clear();
    		nodes.get(5).getStyleClass().add(inactive_context_button_flap_r);
    	}
    	
    	public boolean checkSame(Button refButton)
    	{
    		return(refButton.equals(nodes.get(3)));
    	}
    }

}

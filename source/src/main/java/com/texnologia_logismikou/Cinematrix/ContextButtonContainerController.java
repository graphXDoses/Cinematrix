package com.texnologia_logismikou.Cinematrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;

public class ContextButtonContainerController {

    @FXML
    private HBox ctx_btn_container;
    
    private List<Context> contextButtons;
    private Parent root;
    
    @FXML
    void initialize()
    {
    	assert ctx_btn_container != null : "fx:id \'ctx_btn_container\' was not injected";
		contextButtons = new ArrayList<Context>(getContextButtons());
		
		try {
			for(Context ctx: contextButtons)
			{
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("screens/ContextButton.fxml"));
				root = fxmlLoader.load();
				
				ContextButtonController controller = fxmlLoader.getController();
				controller.setData(ctx);
				
				ctx_btn_container.getChildren().add(root);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    List<Context> getContextButtons()
	{
		List<Context> ls = new ArrayList<>();
		Context ctx;
		
		ctx = new Context("Movies", "images/movie.png");
		ls.add(ctx);
		
		ctx = new Context("Cinemas", "images/theater.png");
		ls.add(ctx);
		
		ctx = new Context("Account", "images/account.png");
		ls.add(ctx);
		
		return(ls);
	}

}

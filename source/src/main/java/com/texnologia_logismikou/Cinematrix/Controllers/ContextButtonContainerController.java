package com.texnologia_logismikou.Cinematrix.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.texnologia_logismikou.Cinematrix.App;
import com.texnologia_logismikou.Cinematrix.Context;
import com.texnologia_logismikou.Cinematrix.Managers.ContextButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;

public class ContextButtonContainerController {

    @FXML
    private HBox ctx_btn_container;
    
    private List<Context> contextButtons;
    
    @FXML
    void initialize()
    {
    	assert ctx_btn_container != null : "fx:id \'ctx_btn_container\' was not injected";
    	/*
    	System.out.println(ctx_btn_container);
    	while((root = ctx_btn_container.getParent()) != null) { }
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
		*/
    }
    
    List<Context> getContextButtons()
	{
		List<Context> ls = new ArrayList<>();
		/*
		Context ctx;
		
		ctx = new Context("Movies", "images/movie.png");
		ls.add(ctx);
		
		ctx = new Context("Cinemas", "images/theater.png");
		ls.add(ctx);
		
		ctx = new Context("Account", "images/account.png");
		ls.add(ctx);
		
    	 */
		return(ls);
	}
    
    public void addContextButton(ContextButton button)
    {
    	ctx_btn_container.getChildren().add(button.getParent());
    }

}

package com.texnologia_logismikou.Cinematrix;

import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class LicensePopup extends Dialog<ButtonType> {

	private static LicensePopup instance = null;
	private static PropertiesReader reader = null;
	
	private LicensePopup()
	{
		try {
			reader = new PropertiesReader("properties-from-pom.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DialogPane dialogPane = new DialogPane() {
            @Override
            protected Node createButtonBar() {
                ButtonBar buttonBar = (ButtonBar) super.createButtonBar();
                buttonBar.setButtonOrder(ButtonBar.BUTTON_ORDER_NONE);

                return buttonBar;
            }
        };
        setDialogPane(dialogPane);
        dialogPane.getButtonTypes().addAll(ButtonType.CLOSE);
        dialogPane.setContentText(MITLicense());

        Region spacer = new Region();
        ButtonBar.setButtonData(spacer, ButtonBar.ButtonData.BIG_GAP);
        HBox.setHgrow(spacer, Priority.ALWAYS);
        dialogPane.applyCss();
        HBox hbox = (HBox) dialogPane.lookup(".container");
        hbox.getChildren().add(spacer);
        setHeaderText(reader.getProperty("AppName") + " - " + reader.getProperty("AppVersion"));
        
        Stage stage = (Stage)getDialogPane().getScene().getWindow();
        stage.setWidth(300);
        stage.setHeight(400);
        stage.setTitle("Software License");
        stage.getIcons().add(new Image(getClass().getResource("images/CinematrixIcon.png").toExternalForm()));
	}
	
	public static LicensePopup getInstance()
	{
		if(instance == null)
		{
			instance = new LicensePopup();
		}
		return(instance);
	}
	
	private static String MITLicense()
	{
		String res = "MIT License\r\n"
				+ "\r\n"
				+ "Copyright (c) 2024 ";
		res += reader.getProperty("TeamNo") + " Group | Software Technology(CSC402) | DAI@UOM\r\n";
		res += "\r\n"
				+ "Permission is hereby granted, free of charge, to any person obtaining a copy\r\n"
				+ "of this software and associated documentation files (the \"Software\"), to deal\r\n"
				+ "in the Software without restriction, including without limitation the rights\r\n"
				+ "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\r\n"
				+ "copies of the Software, and to permit persons to whom the Software is\r\n"
				+ "furnished to do so, subject to the following conditions:\r\n"
				+ "\r\n"
				+ "The above copyright notice and this permission notice shall be included in all\r\n"
				+ "copies or substantial portions of the Software.\r\n"
				+ "\r\n"
				+ "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\r\n"
				+ "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\r\n"
				+ "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\r\n"
				+ "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\r\n"
				+ "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\r\n"
				+ "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\r\n"
				+ "SOFTWARE.";
		
		return(res);
	}
}

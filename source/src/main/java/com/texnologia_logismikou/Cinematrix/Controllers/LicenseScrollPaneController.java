package com.texnologia_logismikou.Cinematrix.Controllers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import com.texnologia_logismikou.Cinematrix.PropertiesReader;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class LicenseScrollPaneController {

    @FXML
    private URL location;

    @FXML
    private Hyperlink link;

    @FXML
    private TextFlow permissions;
    
    @FXML
    private ScrollPane scroller;
    
    private String github_repo_link = null;

    @FXML
    void initialize() {
        assert link != null : "fx:id=\"link\" was not injected: check your FXML file 'AboutUsPopUp.fxml'.";
        assert permissions != null : "fx:id=\"permissions\" was not injected: check your FXML file 'AboutUsPopUp.fxml'.";
        
        link.setText(PropertiesReader.getProperty("TeamNo") + " Group | Software Technology(CSC402) | DAI@UOM");
        github_repo_link = PropertiesReader.getProperty("RepoLink");
        
        Label txt = new Label(MITLicense());
        txt.setTextAlignment(TextAlignment.CENTER);
        txt.setStyle("-fx-text-fill: -fx-txtColor;");
        
        permissions.getChildren().add(txt);
//        permissions.setTextAlignment(TextAlignment.CENTER);
        
        scroller.getStyleClass().clear();
		scroller.getStyleClass().add(".scroll-bar");
//		scroller.setStyle("-fx-backgroud-color: -fx-fg;");
    }
    
    private static String MITLicense()
	{
		String res = 
				  "Permission is hereby granted, free of charge, to any person obtaining a copy\r\n"
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
    
    @FXML
    void gotoGithubRepo()
    {
    	try {
			Desktop.getDesktop().browse(new URI(github_repo_link));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
    }

}

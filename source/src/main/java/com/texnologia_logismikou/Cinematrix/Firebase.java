package com.texnologia_logismikou.Cinematrix;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class Firebase {

	private FirebaseApp firebaseApp = null;
	private static final String CREDENTIALS_PATH = "C:/Users/petsi/University/TexLog/firebase_cred_phoebus.json"; 
	
	//There is a chance that the FirebaseApp gets initialized but the JSON file doesn't close properly.
	//This method also sets a Firestore instance.
	public void initializeFirebase() {
		
		try(InputStream serviceAccount = new FileInputStream(CREDENTIALS_PATH)) {
			
			try {
				FirebaseOptions options = FirebaseOptions.builder()
						.setCredentials(GoogleCredentials.fromStream(serviceAccount))
						.build();
				
				try {
					firebaseApp = FirebaseApp.initializeApp(options);
				} catch (Exception e) {
					
					System.out.println("A Firebase app already exists!");
					showExceptions(e);
				}
			} catch (IOException e) {
				
				showExceptions(e);
			}
			
			//JSON needs to be closed.
			serviceAccount.close();
		} catch(IOException e) {
			
			System.out.println("There was an error closing the JSON file. The process will be terminated.");
			System.out.println("Exception details: " + e.toString());
		}
		
		System.out.println("Firebase App created. Info: " + FirebaseApp.getApps().toString());
	}
	
	private void showExceptions(Exception e) {
		
		System.out.println("There was an exception with the Firebase App.\nException details: " + e.toString());
		System.out.println("\n---------------------------------------------\n");
	}
	
	public void deleteApp() {
		
		firebaseApp.delete();
		firebaseApp = null;
	}



	public boolean appExists() {
		if(firebaseApp != null) {
			return true;
		} else {
			return false;
		}
	}
}

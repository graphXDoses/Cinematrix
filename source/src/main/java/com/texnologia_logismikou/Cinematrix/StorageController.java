package com.texnologia_logismikou.Cinematrix;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;
import java.nio.file.Paths;

import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageException;
import com.google.cloud.storage.StorageOptions;

public class StorageController {
	
	Storage storage = null;
	private final String PROJECT_ID = "fir-test-java-1d671";
	private final String BUCKET_ID = "cinematrix_movie_images";
	private final String DOWNLOAD_PATH = "C:/Users/petsi/University/TexLog/Cinematrix/source/src/main/resources/movie_images";
	
	public void initializeStorage() {
		
		try {
			InputStream serviceAccount = new FileInputStream("C:/Users/petsi/University/TexLog/cinematrix_creds.json");
			try {
				storage = StorageOptions.newBuilder().setProjectId(PROJECT_ID).setCredentials(GoogleCredentials.fromStream(serviceAccount)).build().getService();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeStorage() {
		
		try {
			storage.close();
		} catch (Exception e) {
			showExceptions(e);
		}
		
		storage = null;
	}
	
	public void downloadImage(String imageName) {
		
		try {
			Blob blob = storage.get(BlobId.of(BUCKET_ID, imageName + ".png"));
			try {
				blob.downloadTo(Paths.get(DOWNLOAD_PATH + "/" + imageName + ".png"));
			} catch (Exception e) {
				showExceptions(e);
			}
		} catch (Exception e) {
			showExceptions(e);
		}
	}
	
	private void showExceptions(Exception e) {
		
		System.out.println("There was an exception with Cloud Storage.\nException details: " + e.toString());
		System.out.println("\n---------------------------------------------\n");
	}
	
	public boolean storageExists() {
		
		if(storage == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void printAllBucketObjects() {
		
		Page<Blob> blobs = storage.list(BUCKET_ID);
		
		for (Blob blob : blobs.iterateAll()) {
			System.out.println(blob.getName());
		}
	}
}

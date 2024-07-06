package com.texnologia_logismikou.Cinematrix;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.StringField;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class StorageHandler {
	
	private final String bucketName = "cinematrix_movie_images";
	private final String projectId = "fir-test-java-1d671";
	private final String credentialsPath = "C:/Users/petsi/University/TexLog/cinematrix_creds.json";
	
	private static StorageHandler instance = null;
	
	private StorageHandler() {
		
	}
	
	public static StorageHandler getInstance() {
		
		if(instance==null) {
			instance = new StorageHandler();
		}
		return instance;
	}
	
	public void downloadMovieImage(String movieName) throws FileNotFoundException, Exception {
		// The ID of your GCP project
		// String projectId = "your-project-id";

		// The ID of your GCS bucket
		// String bucketName = "your-unique-bucket-name";

		// The ID of your GCS object
		// String objectName = "your-object-name";

		// The path to which the file should be downloaded
		// String destFilePath = "/local/path/to/file.txt";

		movieName = StringField.toPascalCase(movieName);
		String imageName = "_" + movieName + "_Cover.jpg";
		
		FileInputStream creds = new FileInputStream(credentialsPath); // <--- Path to credentials.
		
		Storage storage = StorageOptions.newBuilder()
				.setProjectId(projectId)
				.setCredentials(GoogleCredentials.fromStream(creds))
				.build()
				.getService();

		Blob blob = storage.get(BlobId.of(bucketName, imageName));
		blob.downloadTo(Paths.get("src/main/resources/com/texnologia_logismikou/Cinematrix/images/" + imageName));
	}
}

package com.texnologia_logismikou.Cinematrix;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class StorageHandler {
	
	public static void downloadObject() throws FileNotFoundException, Exception {
		// The ID of your GCP project
		// String projectId = "your-project-id";

		// The ID of your GCS bucket
		// String bucketName = "your-unique-bucket-name";

		// The ID of your GCS object
		// String objectName = "your-object-name";

		// The path to which the file should be downloaded
		// String destFilePath = "/local/path/to/file.txt";

		FileInputStream creds = new FileInputStream("C:/Users/petsi/University/TexLog/cinematrix_creds.json");
		
		Storage storage = StorageOptions.newBuilder()
				.setProjectId("fir-test-java-1d671")
				.setCredentials(GoogleCredentials.fromStream(creds))
				.build()
				.getService();

		Blob blob = storage.get(BlobId.of("cinematrix_movie_images", "bullet_train.png"));
		blob.downloadTo(Paths.get("src/main/resources/com/texnologia_logismikou/Cinematrix/images/bullet_train.png"));
	}
}

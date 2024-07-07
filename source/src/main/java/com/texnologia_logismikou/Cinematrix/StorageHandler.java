package com.texnologia_logismikou.Cinematrix;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.storage.transfermanager.ParallelDownloadConfig;
import com.google.cloud.storage.transfermanager.TransferManager;
import com.google.cloud.storage.transfermanager.TransferManagerConfig;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.StringField;
import com.google.cloud.storage.transfermanager.DownloadResult;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import com.google.cloud.storage.BlobInfo;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;


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
	
	/**
	 * 	Download a movie image.
	 * @param String The movie's name.
	 * @return Path The path to the image.
	 */
	public Path downloadMovieImage(String movieName) throws FileNotFoundException, IOException {

		movieName = StringField.toPascalCase(movieName);
		String imageName = "_" + movieName + "_Cover.jpg";
		
		FileInputStream creds = new FileInputStream(credentialsPath); // <--- Path to credentials.
		
		Storage storage = StorageOptions.newBuilder()
				.setProjectId(projectId)
				.setCredentials(GoogleCredentials.fromStream(creds))
				.build()
				.getService();

		Blob blob = storage.get(BlobId.of(bucketName, imageName));
		Path path = Paths.get("src/main/resources/com/texnologia_logismikou/Cinematrix/images/" + imageName);
		blob.downloadTo(path);
		
		return path;
	}
	
	/**
	 *	This method doesn't work for some reason. Placing as deprecated until we find a fix.
	 *  @deprecated
	 */
	public void downloadAllMovieImages() throws FileNotFoundException, IOException {
		 
		 Path dir = Paths.get("src/main/resources/com/texnologia_logismikou/Cinematrix/images/");
		 FileInputStream creds = new FileInputStream(credentialsPath); // <--- Path to credentials.
		 
		 Storage storage = StorageOptions.newBuilder()
				 .setProjectId(projectId)
				 .setCredentials(GoogleCredentials.fromStream(creds))
				 .build()
				 .getService();
		 List<BlobInfo> blobs = storage.list(bucketName)
				 .streamAll()
				 .map(blob -> blob.asBlobInfo())
				 .collect(Collectors.toList());
		 
		 TransferManager transferManager = TransferManagerConfig.newBuilder()
				 .build()
				 .getService();
		 ParallelDownloadConfig parallelDownloadConfig = ParallelDownloadConfig.newBuilder()
				 .setBucketName(bucketName)
				 .setDownloadDirectory(dir)
				 .build();

		 List<DownloadResult> results = transferManager.downloadBlobs(blobs, parallelDownloadConfig).getDownloadResults();
		 
		 for (DownloadResult result : results) {
		      System.out.println(
		          "Download of "
		              + result.getInput().getName()
		              + " completed with status "
		              + result.getStatus() + " "
		              + result.getException() + " ");
		 }
	}
	 
	public void downloadMovieImages(String[] movieNames) throws IOException {
		 
		for(String movieName: movieNames) {
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
}

package com.texnologia_logismikou.Cinematrix;

import java.nio.file.Paths;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class StorageController {
	
	Firebase firebase = new Firebase();
	
	public void downloadImage() {
		
		Storage storage = StorageOptions.newBuilder().setProjectId("fir-test-java-1d671").build().getService();
		
		Blob blob = storage.get(BlobId.of("fir-test-java-1d671.appspot.com", "rush_hour.png"));
		blob.downloadTo(Paths.get("C:/Users/petsi/University/TexLog/rush_hour.png"));
	}
}

package com.texnologia_logismikou.Cinematrix;

import java.nio.file.Paths;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class StorageController {

	Storage storage = null;
	
	public void downloadImage() {
		
		storage = StorageOptions.newBuilder().setProjectId(null).build().getService();
		
		Blob blob = storage.get(BlobId.of("fir-test	-java-1d671", "rush_hour.png"));
		blob.downloadTo(Paths.get("C:/Users/petsi/University/TexLog"));
		
	}
}

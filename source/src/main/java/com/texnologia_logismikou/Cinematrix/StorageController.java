package com.texnologia_logismikou.Cinematrix;

import java.nio.file.Paths;

import com.google.api.services.storage.model.Bucket;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class StorageController {

	Storage storage = null;
	
	public void downloadImage() {
		
		storage = StorageOptions.getDefaultInstance().getService();
		com.google.cloud.storage.Bucket bucket = storage.create(BucketInfo.of("fir-test-java-1d671"));
		
		System.out.println(bucket.getName());
	}
}

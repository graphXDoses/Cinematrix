package com.texnologia_logismikou.Cinematrix;

import java.util.ArrayList;
import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;

public class FirestoreDB {

	private Firestore db = null;
	
	public void setDatabase() {
		
		db = com.google.firebase.cloud.FirestoreClient.getFirestore();
	}
	
	public void deleteDatabase() {
		
		try {
			db.close();
		} catch (Exception e) {
			showExceptions(e);
		}
	}
	
	public boolean addMovie(Movie movie) {
		
		ApiFuture<WriteResult> future = db.collection("Movies").document(movie.getTitle()).set(movie, SetOptions.merge());
		
		try {
			System.out.println("Database updated at: " + future.get().getUpdateTime());
			return true;
			//Is Firestore closing after every action?
		} catch (Exception e) {
			showExceptions(e);
		}
		
		return false;
	}

	public ArrayList<Movie> fetchAllMovies() {
		
		ArrayList<Movie> allMovies = new ArrayList<Movie>();
		
		CollectionReference colRef = null;
		
		colRef = db.collection("Movies");
		ApiFuture<QuerySnapshot> future = colRef.get();
		
		try {
			List<QueryDocumentSnapshot> docs = future.get().getDocuments();
			for(QueryDocumentSnapshot doc : docs) {
				allMovies.add(doc.toObject(Movie.class));
			}
			System.out.println("The snapshot was read in: " + future.get().getReadTime());
		} catch (Exception e) {
			showExceptions(e);
		}
		
		return allMovies;
	}

	public boolean addUser(Customer user) {
		
		ApiFuture<WriteResult> future = db.collection("Users").document(user.getFull_name()).set(user, SetOptions.merge());
		
		try {
			System.out.println("Firestore updated at: " + future.get().getUpdateTime());
			return true;
		} catch (Exception e) {
			showExceptions(e);
		}
		
		return false;
	}
	
	public Customer fetchUser(String username) {
		
		Customer user = null;
		
		DocumentReference docRef = db.collection("Users").document(username);
		ApiFuture<DocumentSnapshot> future = docRef.get();
		try {
			DocumentSnapshot doc = future.get();
			
			if(!doc.exists()) {
				return null;
			}
			
			return user = doc.toObject(Customer.class);
		} catch (Exception e) {
			showExceptions(e);
			return null;
		}
	}
	
	private void showExceptions(Exception e) {
		
		System.out.println("There was an exception with Firestore.\nException details: " + e.toString());
		System.out.println("\n---------------------------------------------\n");
	}
	
	public boolean dbExists() {
		if(db != null) {
			return true;
		} else {
			return false;
		}
	}
}

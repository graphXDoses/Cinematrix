package com.texnologia_logismikou.Cinematrix;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.*;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.*;
import com.texnologia_logismikou.Cinematrix.RequestBodies.*;
import com.texnologia_logismikou.Cinematrix.ResponseBodies.*;

public class RequestHandler {

	private static RequestHandler instance = null;
	
	private final String documentsPath = "projects/fir-test-java-1d671/databases/(default)/documents";
	
	private RequestHandler() {
		
	}
	
	public static RequestHandler getInstance() {
		
		if(instance == null) {
			instance = new RequestHandler();
		}
		
		return instance;
	}
	
	public SignInResponseBody signInRequest(String email, String password, String webKey) throws URISyntaxException, IOException, InterruptedException {
		
		/*
		 *  Common Errors
		 *  
	     *	EMAIL_NOT_FOUND: There is no user record corresponding to this identifier. The user may have been deleted.
	     *	INVALID_PASSWORD: The password is invalid or the user does not have a password.
	     *	USER_DISABLED: The user account has been disabled by an administrator.
		 */
		
		SignInRequestBody request = new SignInRequestBody(email, password);
		SignInResponseBody response = new SignInResponseBody();
		
		// Request ---> JSON
		Gson gson = new Gson();
		String jsonRequest = gson.toJson(request);
		
		HttpRequest postRequest = HttpRequest.newBuilder()
				.uri(new URI("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + webKey))
				.POST(BodyPublishers.ofString(jsonRequest))
				.build();
		
		//The client sends the request, the response stores it.
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpResponse<String> postResponse = httpClient.send(postRequest, BodyHandlers.ofString());
		
		// JSON ---> Response
		response = gson.fromJson(postResponse.body(), SignInResponseBody.class);
		
		return response;
	}
	
	public SignUpResponseBody SignUpRequest(String email, String password, String webKey) throws URISyntaxException, InterruptedException, IOException {
		
		/*
		 * 	Common Errors
		 * 
    	 *	EMAIL_EXISTS: The email address is already in use by another account.
    	 *	OPERATION_NOT_ALLOWED: Password sign-in is disabled for this project.
    	 *	TOO_MANY_ATTEMPTS_TRY_LATER: We have blocked all requests from this device due to unusual activity. Try again later.
		 */
		
		SignUpRequestBody request = new SignUpRequestBody(email, password);
		SignUpResponseBody response = new SignUpResponseBody();
		
		Gson gson = new Gson();
		String jsonRequest = gson.toJson(request);
		
		HttpRequest postRequest = HttpRequest.newBuilder()
				.uri(new URI("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + webKey))
				.POST(BodyPublishers.ofString(jsonRequest))
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> postResponse = client.send(postRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(postResponse.body(), SignUpResponseBody.class);
		
		return response;
	}
	
	public void resetPasswordRequest(String email, String webKey) throws IOException, URISyntaxException, InterruptedException {
		
		ResetPasswordRequestBody request = new ResetPasswordRequestBody(email);
		
		Gson gson = new Gson();
		String jsonRequest = gson.toJson(request);
		
		HttpRequest postRequest = HttpRequest.newBuilder()
				.uri(new URI("https://identitytoolkit.googleapis.com/v1/accounts:sendOobCode?key=" + webKey))
				.setHeader("Content-Type", "application/json")
				.POST(BodyPublishers.ofString(jsonRequest))
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> postResponse = client.send(postRequest, BodyHandlers.ofString());
		
		System.out.println("STATUS CODE: " + postResponse.statusCode());
		switch(postResponse.statusCode()) {
		case 200: System.out.println("Reset password email sent succesfully."); break;
		default: System.out.println("Couldn't send reset password email.");
		}
	}

	public void deleteUserAccountRequest(String firebaseId, String webKey) throws URISyntaxException, IOException, InterruptedException {
		
		DeleteAccountRequestBody request = new DeleteAccountRequestBody();
		request.setIdToken(firebaseId);
		
		Gson gson = new Gson();
		String jsonRequest = gson.toJson(request);
		
		HttpRequest postRequest = HttpRequest.newBuilder()
				.uri(new URI("https://identitytoolkit.googleapis.com/v1/accounts:delete?key=" + webKey))
				.POST(BodyPublishers.ofString(jsonRequest))
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> postResponse = client.send(postRequest, BodyHandlers.ofString());
		
		System.out.println("STATUS CODE: " + postResponse.statusCode());
		switch(postResponse.statusCode()) {
		case 200: System.out.println("Account deleted succsefully."); break;
		default: System.out.println("Couldn't delete account.");
		}
		
		/*
		 *  Doesn't return anything.
		 */
	}

	public UserDocument createUserDocumentRequest(String uid, String firebaseId) throws URISyntaxException, InterruptedException, IOException {
		
		UserDocument response = new UserDocument();
		
		Gson gson = new Gson();
		
		HttpRequest postRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1beta1/" + documentsPath + "/Users?documentId=" + uid))
				.POST(BodyPublishers.ofString(""))
				.setHeader("Authorization", "Bearer " + firebaseId)
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> postResponse = client.send(postRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(postResponse.body(), UserDocument.class);
		
		return response;
	}
	
	public UserDocument getUserDocumentRequest(String uid, String firebaseId) throws URISyntaxException, IOException, InterruptedException  {
		
		UserDocument response = new UserDocument();
		
		Gson gson = new Gson();
		
		HttpRequest getRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1/" + documentsPath + "/Users/" + uid))
				.GET()
				.setHeader("Authorization", "Bearer " + firebaseId)
				.setHeader("Content-Type", "application/json")
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> getResponse = client.send(getRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(getResponse.body(), UserDocument.class);
		
		return response;
	}

	public UserDocument updateUserDocumentRequest(String uid, String firebaseId, UserFields fields) throws URISyntaxException, InterruptedException, IOException {
		
		String queryParameters = UpdateMaskQuery.createUpdateAllFieldsQuery(UpdateMaskQuery.userFieldNames);
		
		UserDocument request = new UserDocument();
		UserDocument response = new UserDocument();
		
		request.setFields(fields);
		
		Gson gson = new Gson();
		String jsonRequest = gson.toJson(request);
		
		HttpRequest patchRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1beta1/" + documentsPath +"/Users/" + uid + "?" + queryParameters))
				.method("PATCH", BodyPublishers.ofString(jsonRequest))
				.setHeader("Authorization", "Bearer " + firebaseId)
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> patchResponse = client.send(patchRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(patchResponse.body(), UserDocument.class);

		return response;
	}
	
	public void deleteUserDocumentRequest(String uid, String firebaseId) throws IOException, URISyntaxException, InterruptedException {
		
		HttpRequest deleteRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1/" + documentsPath + "/Users/" + uid))
				.DELETE()
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> deleteResponse = client.send(deleteRequest, BodyHandlers.ofString());
		
		System.out.println("STATUS CODE: " + deleteResponse.statusCode());
		switch(deleteResponse.statusCode()) {
		case 200: System.out.println("Account document deleted succsefully."); break;
		default: System.out.println("Couldn't delete account document.");
		}
	}

	public MovieDocument createMovieDocumentRequest(String name, String firebaseId) throws URISyntaxException, IOException, InterruptedException {
		
		name = StringField.toPascalCase(name);
		MovieDocument response = new MovieDocument();
		
		Gson gson = new Gson();
		
		HttpRequest postRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1/" + documentsPath + "/Movies?documentId=" + name))
				.POST(BodyPublishers.ofString(""))
				.setHeader("Authorization", "Bearer " + firebaseId)
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> postResponse = client.send(postRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(postResponse.body(), MovieDocument.class);
		
		System.out.println(postResponse.body());
		
		return response;
	}
	
	public MovieDocument getMovieDocumentRequest(String name) throws IOException, InterruptedException, URISyntaxException {
		
		name = StringField.toPascalCase(name);
		
		MovieDocument response = new MovieDocument();
		Gson gson = new Gson();
		
		HttpRequest getRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1/" + documentsPath + "/Movies/" + name))
				.GET()
				.setHeader("Content-Type", "application/json")
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> getResponse = client.send(getRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(getResponse.body(), MovieDocument.class);
				
		return response;
	}

	public MovieDocument updateMovieDocumentRequest(MovieFields fields, String firebaseId) throws URISyntaxException, IOException, InterruptedException {
		
		/*
		 * 	If a value doesn't get updated upon request and no errors occur check the UpdateMaskQuery.java class. 
		 */
		
		String name = StringField.toPascalCase(fields.getTitle().getStringValue());
		String queryParameter = UpdateMaskQuery.createUpdateAllFieldsQuery(UpdateMaskQuery.movieFieldNames);
		
		MovieDocument request = new MovieDocument();
		MovieDocument response = new MovieDocument();
		request.setFields(fields);
		
		Gson gson = new Gson();
		String jsonRequest = gson.toJson(request);
		
		HttpRequest patchRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1/" + documentsPath + "/Movies/" + name + "?" + queryParameter))
				.method("PATCH", BodyPublishers.ofString(jsonRequest))
				.setHeader("Authorization", "Bearer " + firebaseId)
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> patchResponse = client.send(patchRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(patchResponse.body(), MovieDocument.class);
		
		System.out.println(patchResponse.body());
		
		return response;
	}
	
	public int deleteMovieDocumentRequest(String name) throws URISyntaxException, IOException, InterruptedException {
		
		name = StringField.toPascalCase(name);
		
		HttpRequest deleteRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1/" + documentsPath + "/Movies/" + name))
				.DELETE()
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> deleteResponse = client.send(deleteRequest, BodyHandlers.ofString());
		
		return deleteResponse.statusCode();
	}

	public RoomDocument createRoomDocumentRequest(String firebaseId, String roomId, String cinemaName) throws IOException, URISyntaxException, InterruptedException {
		
		RoomDocument response = new RoomDocument();
		
		Gson gson = new Gson();
		
		HttpRequest postRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1beta1/" + documentsPath + "/Cinemas/" + cinemaName + "/Venues?documentId=" + roomId))
				.POST(BodyPublishers.ofString(""))
				.setHeader("Authorization", "Bearer " + firebaseId)
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> postResponse = client.send(postRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(postResponse.body(), RoomDocument.class);
		
		return response;
	}
	
	public RoomDocument getRoomDocumentRequest(String roomId, String cinemaName) throws IOException, URISyntaxException, InterruptedException {
		
		RoomDocument response = new RoomDocument();
		Gson gson = new Gson();
		
		HttpRequest getRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1/" + documentsPath + "/Cinemas/" + cinemaName + "/Venues/" + roomId))
				.GET()
				.setHeader("Content-Type", "application/json")
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> getResponse = client.send(getRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(getResponse.body(), RoomDocument.class);
		return response;
	}
	
	public RoomDocument updateRoomDocumentRequest(String firebaseId, RoomFields fields) throws URISyntaxException, IOException, InterruptedException {
		
		String queryParameter = UpdateMaskQuery.createUpdateAllFieldsQuery(UpdateMaskQuery.roomFieldNames);
		
		RoomDocument request = new RoomDocument();
		RoomDocument response = new RoomDocument();
		request.setFields(fields);
		
		Gson gson = new Gson();
		String jsonRequest = gson.toJson(request);
		
		HttpRequest patchRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1/" + documentsPath + "/Cinemas/Vakoura/Venues/Room1?" + queryParameter))
				.method("PATCH", BodyPublishers.ofString(jsonRequest))
				.setHeader("Authorization", "Bearer " + firebaseId)
				.build();
			
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> patchResponse = client.send(patchRequest, BodyHandlers.ofString());
		
		//System.out.println(patchResponse.body());
		
		response = gson.fromJson(patchResponse.body(), RoomDocument.class);
		
		System.out.println(patchResponse.body());
		
		return response;
	}
	
	public void deleteRoomDocumentRequest(String cinemaName, String roomId) throws URISyntaxException, IOException, InterruptedException {
		
		HttpRequest deleteRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1/" + documentsPath + "/Cinemas/" + cinemaName + "/Venues/" + roomId))
				.DELETE()
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> deleteResponse = client.send(deleteRequest, BodyHandlers.ofString());
		
		System.out.println("STATUS CODE: " + deleteResponse.statusCode());
		switch(deleteResponse.statusCode()) {
		case 200: System.out.println("Room document deleted succsefully."); break;
		default: System.out.println("Couldn't delete room document.");
		}
	}
}

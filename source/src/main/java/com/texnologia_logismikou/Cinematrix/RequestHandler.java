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
	
	private final String webKey;
	
	private final String documentsPath = "projects/fir-test-java-1d671/databases/(default)/documents";
	
	private RequestHandler(String webKey) {
		
		this.webKey = webKey;
	}
	
	public static RequestHandler getInstance(String webKey) {
		
		if(instance == null) {
			instance = new RequestHandler(webKey);
		}
		
		return instance;
	}
	
	public SignInResponseBody signInRequest(String email, String password) throws URISyntaxException, IOException, InterruptedException {
		
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
	
	public SignUpResponseBody SignUpRequest(String email, String password) throws URISyntaxException, InterruptedException, IOException {
		
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
	
	@SuppressWarnings("unchecked")
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
	
	public UserDocument updateUserDocumentRequest(String uid, String firebaseId, UserFields fields) throws URISyntaxException, InterruptedException, IOException {
		
		UserDocument request = new UserDocument();
		UserDocument response = new UserDocument();
		
		request.setFields(fields);
		
		Gson gson = new Gson();
		String jsonRequest = gson.toJson(request);
		
		HttpRequest patchRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1beta1/" + documentsPath +"/Users/" + uid + "?updateMask.fieldPaths=name&updateMask.fieldPaths=email&updateMask.fieldPaths=admin"))
				.method("PATCH", BodyPublishers.ofString(jsonRequest))
				.setHeader("Authorization", "Bearer " + firebaseId)
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> patchResponse = client.send(patchRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(patchResponse.body(), UserDocument.class);

		return response;
	}
	
	public void deleteUserAccount(String firebaseId) throws URISyntaxException, IOException, InterruptedException {
		
		DeleteAccountRequestBody request = new DeleteAccountRequestBody();
		request.setIdToken(firebaseId);
		
		Gson gson = new Gson();
		String jsonRequest = gson.toJson(request);
		
		HttpRequest postRequest = HttpRequest.newBuilder()
				.uri(new URI("https://identitytoolkit.googleapis.com/v1/accounts:delete?key=" + webKey))
				.POST(BodyPublishers.ofString(jsonRequest))
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> response = client.send(postRequest, BodyHandlers.ofString());
		
		System.out.println("STATUS CODE: " + response.statusCode());
		
		/*
		 *  Doesn't return anything.
		 */
	}
	
	public UserDocument getUserDocument(String uid, String firebaseId) throws URISyntaxException, IOException, InterruptedException  {
		
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
	
	public MovieDocument createMovieDocumentRequest(String name, String firebaseId) throws URISyntaxException, IOException, InterruptedException {
		
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
	
	public MovieDocument updateMovieDocumentRequest(MovieFields fields, String firebaseId) throws URISyntaxException, IOException, InterruptedException {
		
		MovieDocument request = new MovieDocument();
		MovieDocument response = new MovieDocument();
		request.setFields(fields);
		
		Gson gson = new Gson();
		String jsonRequest = gson.toJson(request);
		
		HttpRequest patchRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1/" + documentsPath + "/Movies/" + fields.getName().getStringValue() + "?updateMask.fieldPaths=name&updateMask.fieldPaths=cinemas&updateMask.fieldPaths=duration"))
				.method("PATCH", BodyPublishers.ofString(jsonRequest))
				.setHeader("Authorization", "Bearer " + firebaseId)
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> patchResponse = client.send(patchRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(patchResponse.body(), MovieDocument.class);
		
		System.out.println(patchResponse.body());
		System.out.println(response.getFields().getDuration().getDoubleValue());
		
		return null;
	}
	
	
}

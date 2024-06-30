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
import com.texnologia_logismikou.Cinematrix.DocumentObjects.UserDocument;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.NameField;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.UserFields;
import com.texnologia_logismikou.Cinematrix.RequestBodies.*;
import com.texnologia_logismikou.Cinematrix.ResponseBodies.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RequestController {

	// This webkey is safe to be published on GitHub as it is not the same as an API key.
	// It only references our Firebase project and that's all.
	private static final String webKey = "AIzaSyDTn8MSxkAuIX-sH-_I_vwAwVqIt77sORU";
	
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
		
		if(response.getError() == null) {
			System.out.println("Succesful sign in!");
		} else {
			System.out.println(response.getError().getMessage());
		}
		
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
		
		if(response.getError() == null) {
			System.out.println("User succesfully created with uid " + response.getLocalId());
		} else {
			System.out.println(response.getError().getMessage());
		}
		
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public CreateDocumentResponseBody createUserDocumentRequest(String uid, String firebaseToken) throws URISyntaxException, InterruptedException, IOException {
		
		CreateDocumentResponseBody response = new CreateDocumentResponseBody();
		
		Gson gson = new Gson();
		
		HttpRequest postRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1beta1/projects/fir-test-java-1d671/databases/(default)/documents/Users?documentId=" + uid))
				.POST(BodyPublishers.ofString(""))
				.setHeader("Authorization", "Bearer " + firebaseToken)
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> postResponse = client.send(postRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(postResponse.body(), CreateDocumentResponseBody.class);
		
		if(response.getError() == null) {
			System.out.println("User document created at: " + response.getCreateTime());
		} else {
			System.out.println(response.getError().getMessage());
		}
		
		return response;
	}
	
	public void updateDocumentRequest(String token) throws URISyntaxException, InterruptedException, IOException {
		
		UserDocument request = new UserDocument();
		UserDocument response = new UserDocument();
		
		UserFields fields = new UserFields();
		NameField nameField = new NameField();
		nameField.setStringValue("FOIVOS!!!");
		fields.setName(nameField);
		request.setFields(fields);
		
		Gson gson = new Gson();
		String jsonRequest = gson.toJson(request);
		
		HttpRequest patchRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1beta1/projects/fir-test-java-1d671/databases/(default)/documents/Users/Dg3SViArdWgyijYzzvmjXhMcAc32?updateMask.fieldPaths=name"))
				.method("PATCH", BodyPublishers.ofString(jsonRequest))
				.setHeader("Authorization", "Bearer " + token)
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> patchResponse = client.send(patchRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(patchResponse.body(), UserDocument.class);
		
		System.out.println(response);
	}
}

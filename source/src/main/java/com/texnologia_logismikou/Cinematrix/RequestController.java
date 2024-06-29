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
import com.texnologia_logismikou.Cinematrix.Requests.*;
import com.texnologia_logismikou.Cinematrix.Responses.*;

public class RequestController {

	// This webkey is safe to be published on GitHub as it is not the same as an API key.
	// It only references our Firebase project and that's all.
	private static final String webKey = "AIzaSyDTn8MSxkAuIX-sH-_I_vwAwVqIt77sORU";
	
	public void signInRequest() throws URISyntaxException, IOException, InterruptedException {
		
		/*
		 *  Common Errors
		 *  
	     *	EMAIL_NOT_FOUND: There is no user record corresponding to this identifier. The user may have been deleted.
	     *	INVALID_PASSWORD: The password is invalid or the user does not have a password.
	     *	USER_DISABLED: The user account has been disabled by an administrator.
		 */
		
		
		SignInRequest request = new SignInRequest("phoebuspetsi@gmail.com", "myPassword133");
		SignInResponse response = new SignInResponse();
		
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
		response = gson.fromJson(postResponse.body(), SignInResponse.class);
		
		if(response.getError() == null) {
			System.out.println("Succesful sign in!");
		} else {
			System.out.println(response.getError().getMessage());
		}
	}
	
	public void SignUpRequest() throws URISyntaxException, InterruptedException, IOException {
		
		/*
		 * 	Common Errors
		 * 
    	 *	EMAIL_EXISTS: The email address is already in use by another account.
    	 *	OPERATION_NOT_ALLOWED: Password sign-in is disabled for this project.
    	 *	TOO_MANY_ATTEMPTS_TRY_LATER: We have blocked all requests from this device due to unusual activity. Try again later.
		 */
		
		SignUpRequest request = new SignUpRequest("phoebuspetsi@gmail.com", "anotherPassword123");
		SignUpResponse response = new SignUpResponse();
		
		Gson gson = new Gson();
		String jsonRequest = gson.toJson(request);
		
		HttpRequest postRequest = HttpRequest.newBuilder()
				.uri(new URI("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + webKey))
				.POST(BodyPublishers.ofString(jsonRequest))
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> postResponse = client.send(postRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(postResponse.body(), SignUpResponse.class);
		
		if(response.getError() == null) {
			System.out.println("User succesfully created");
		} else {
			System.out.println(response.getError().getMessage());
		}
	}
}

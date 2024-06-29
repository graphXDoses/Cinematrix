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
import com.texnologia_logismikou.Cinematrix.Requests.SignInRequest;
import com.texnologia_logismikou.Cinematrix.Responses.SignInResponse;

public class RequestController {

	public static final String webKey = "AIzaSyDTn8MSxkAuIX-sH-_I_vwAwVqIt77sORU";
	
	public void signInRequest() throws URISyntaxException, IOException, InterruptedException {
		
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
}

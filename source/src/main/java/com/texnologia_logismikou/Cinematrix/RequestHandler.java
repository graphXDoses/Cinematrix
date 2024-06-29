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
import com.google.gson.JsonObject;

public class RequestHandler {

	public static final String webKey = "AIzaSyDTn8MSxkAuIX-sH-_I_vwAwVqIt77sORU";
	
	public void signInRequest() throws URISyntaxException, IOException, InterruptedException {
		
		TemporaryUser user = new TemporaryUser();
		user.setEmail("phoebuspetsi@gmail.com");
		user.setPassword("myPassword321");
		SignInRequest signIn = new SignInRequest(user);
		
		Gson gson = new Gson();
		String jsonRequest = gson.toJson(signIn);
		
		System.out.println(jsonRequest);
		
		HttpRequest postRequest = HttpRequest.newBuilder()
				.uri(new URI("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + webKey))
				.POST(BodyPublishers.ofString(jsonRequest))
				.build();
		
		//The client send the request, the response stores it.
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpResponse<String> postResponse = httpClient.send(postRequest, BodyHandlers.ofString());
		
		signIn = gson.fromJson(postResponse.body(), SignInRequest.class);
		
		System.out.println(signIn.getError().getMessage());
	}
}

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
				
		UserDocument request = new UserDocument();
		UserDocument response = new UserDocument();
		
		request.setFields(fields);
		
		Gson gson = new Gson();
		String jsonRequest = gson.toJson(request);
		
		HttpRequest patchRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1beta1/" + documentsPath +"/Users/" + uid + "?" + fields.createQueryParameter()))
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

	/**
	 * @deprecated Use updateMovieDocumentRequest instead.
	 */
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
				
		MovieDocument request = new MovieDocument();
		MovieDocument response = new MovieDocument();
		request.setFields(fields);
		
		Gson gson = new Gson();
		String jsonRequest = gson.toJson(request);
		
		HttpRequest patchRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1/" + documentsPath + "/Movies/" + StringField.toPascalCase(fields.getTitle().getStringValue()) + "?" + fields.createQueryParameter()))
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

	/**
	 * @deprecated Use updateVenueDocumentRequest instead.
	 */
	public VenueDocument createVenueDocumentRequest(String firebaseId, String roomId, String cinemaName) throws IOException, URISyntaxException, InterruptedException {
		
		VenueDocument response = new VenueDocument();
		
		Gson gson = new Gson();
		
		HttpRequest postRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1beta1/" + documentsPath + "/Cinemas/" + cinemaName + "/Venues?documentId=" + roomId))
				.POST(BodyPublishers.ofString(""))
				.setHeader("Authorization", "Bearer " + firebaseId)
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> postResponse = client.send(postRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(postResponse.body(), VenueDocument.class);
		
		return response;
	}
	
	public VenueDocument getVenueDocumentRequest(String roomId, String cinemaName) throws IOException, URISyntaxException, InterruptedException {
		
		VenueDocument response = new VenueDocument();
		Gson gson = new Gson();
		
		HttpRequest getRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1/" + documentsPath + "/Cinemas/" + cinemaName + "/Venues/" + roomId))
				.GET()
				.setHeader("Content-Type", "application/json")
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> getResponse = client.send(getRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(getResponse.body(), VenueDocument.class);
		return response;
	}
	
	public VenueDocument updateVenueDocumentRequest(String firebaseId, VenueFields fields, String cinemaName) throws URISyntaxException, IOException, InterruptedException {
				
		VenueDocument request = new VenueDocument();
		VenueDocument response = new VenueDocument();
		request.setFields(fields);
		
		Gson gson = new Gson();
		String jsonRequest = gson.toJson(request);
		
		HttpRequest patchRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1/" + documentsPath + "/Cinemas/" + cinemaName + "/Venues/" + StringField.toPascalCase(fields.getName().getStringValue()) + "?" + fields.createQueryParameter()))
				.method("PATCH", BodyPublishers.ofString(jsonRequest))
				.setHeader("Authorization", "Bearer " + firebaseId)
				.build();
			
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> patchResponse = client.send(patchRequest, BodyHandlers.ofString());
		
		//System.out.println(patchResponse.body());
		
		response = gson.fromJson(patchResponse.body(), VenueDocument.class);
		
		System.out.println(patchResponse.body());
		
		return response;
	}
	
	public void deleteVenueDocumentRequest(String cinemaName, String roomId) throws URISyntaxException, IOException, InterruptedException {
		
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
	
	/**
	 *  @deprecated Use updateCinemaDocumentRequest instead.
	 */
	public CinemaDocument createCinemaDocumentRequest(String name, String firebaseId) throws URISyntaxException, IOException, InterruptedException {
		
		name = StringField.toPascalCase(name);
		CinemaDocument response = new CinemaDocument();
		
		Gson gson = new Gson();
		
		HttpRequest postRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1/" + documentsPath + "/Cinemas?documentId=" + name ))
				.POST(BodyPublishers.ofString(""))
				.setHeader("Authorization", "Bearer " + firebaseId)
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> postResponse = client.send(postRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(postResponse.body(), CinemaDocument.class);
		
		System.out.println(postResponse.body());
		
		return response;
	}
	
	public CinemaDocument updateCinemaDocumentRequest(CinemaFields fields, String firebaseId) throws URISyntaxException, IOException, InterruptedException {
				
		CinemaDocument request = new CinemaDocument();
		CinemaDocument response = new CinemaDocument();
		request.setFields(fields);
		
		Gson gson = new Gson();
		String jsonRequest = gson.toJson(request);
		
		HttpRequest patchRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1/" + documentsPath + "/Cinemas/" + StringField.toPascalCase(fields.getName().getStringValue()) + "?" + fields.createQueryParameter()))
				.method("PATCH", BodyPublishers.ofString(jsonRequest))
				.setHeader("Authorization", "Bearer " + firebaseId)
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> patchResponse = client.send(patchRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(patchResponse.body(), CinemaDocument.class);
		
		System.out.println(patchResponse.body());
		
		return response;
	}
	
	public ScreeningDocument createScreeningDocumentRequest(String firebaseId, ScreeningFields fields) throws URISyntaxException, IOException, InterruptedException {
		
		ScreeningDocument request = new ScreeningDocument();
		ScreeningDocument response = new ScreeningDocument();
		request.setFields(fields);
		
		Gson gson = new Gson();
		String jsonRequest = gson.toJson(request);
		
		HttpRequest patchRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1/" + documentsPath + "/Screenings/" + fields.getUid().getStringValue()))
				.method("PATCH", BodyPublishers.ofString(jsonRequest))
				//.setHeader("Authorization", "Bearer " + firebaseId)
				.setHeader("Content-Type", "application/json")
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> patchResponse = client.send(patchRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(patchResponse.body(), ScreeningDocument.class);
		
		System.out.println(patchResponse.body());
		
		return response;
	}

	public TicketDocument createTicketDocument(String firebaseId, TicketFields fields, String uid) throws URISyntaxException, IOException, InterruptedException {
		
		TicketDocument request = new TicketDocument();
		TicketDocument response = new TicketDocument();
		request.setFields(fields);
		
		Gson gson = new Gson();
		String jsonRequest = gson.toJson(request);
		
		HttpRequest patchRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1/" + documentsPath + "/Users/" + uid + "/Tickets/" + fields.getUid().getStringValue()))
				.method("PATCH", BodyPublishers.ofString(jsonRequest))
				.setHeader("Authorization", "Bearer " + firebaseId)
				.setHeader("Content-Type", "application/json")
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> patchResponse = client.send(patchRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(patchResponse.body(), TicketDocument.class);
		return response;
	}
	
	public ListVenuesResponseBody fetchAllCinemaVenuesRequest(String cinemaName) throws URISyntaxException, IOException, InterruptedException {
		
		ListVenuesResponseBody response = new ListVenuesResponseBody();
		Gson gson = new Gson();
		
		HttpRequest getRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1/" + documentsPath + "/Cinemas/" + StringField.toPascalCase(cinemaName) + "/Venues"))
				.GET()
				.setHeader("Content-Type", "application/json")
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> getResponse = client.send(getRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(getResponse.body(), ListVenuesResponseBody.class);
			
		return response;
	}
	
	public ListCinemasResponseBody fetchAllCinemasRequest() throws URISyntaxException, IOException, InterruptedException {
		
		ListCinemasResponseBody response = new ListCinemasResponseBody();
		Gson gson = new Gson();
		
		HttpRequest getRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1/" + documentsPath + "/Cinemas"))
				.GET()
				.setHeader("Content-Type", "application/json")
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> getResponse = client.send(getRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(getResponse.body(), ListCinemasResponseBody.class);
				
		return response;
	}
	
	public ListMoviesResponseBody fetchAllMovies() throws URISyntaxException, IOException, InterruptedException {
		
		ListMoviesResponseBody response = new ListMoviesResponseBody();
		Gson gson = new Gson();
		
		HttpRequest getRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1/" + documentsPath + "/Movies"))
				.GET()
				.setHeader("Content-Type", "application/json")
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> getResponse = client.send(getRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(getResponse.body(), ListMoviesResponseBody.class);
						
		return response;
	}
	
	public ListScreeningsResponseBody fetchAllScreenings() throws URISyntaxException, IOException, InterruptedException {
		
		ListScreeningsResponseBody response = new ListScreeningsResponseBody();
		
		Gson gson = new Gson();
		
		HttpRequest getRequest = HttpRequest.newBuilder()
				.uri(new URI("https://firestore.googleapis.com/v1/" + documentsPath + "/Screenings"))
				.GET()
				.setHeader("Content-Type", "application/json")
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> getResponse = client.send(getRequest, BodyHandlers.ofString());
		
		response = gson.fromJson(getResponse.body(), ListScreeningsResponseBody.class);
		return response;
	}
}

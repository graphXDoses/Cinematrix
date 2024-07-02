package com.texnologia_logismikou.Cinematrix;

public class UpdateMaskQuery {

	private static final String querySyntax = "updateMask.fieldPaths=";
	private static final String[] movieFieldNames = {"title", "releaseYear", "mpRating", "duration"
			, "ytTrailerUrl", "categories", "cinemas", "description", "director"};

	private static final String[] userFieldNames = {"admin", "email", "name"};
	
	public static String createUpdateAllMovieFieldsQuery() {
		
		String queryParameter = "";
		
		for(String fieldName: movieFieldNames) {
			queryParameter += querySyntax + fieldName + "&";
		}
		
		queryParameter = queryParameter.substring(0, queryParameter.length() - 1);
		return queryParameter;
	}
	
	public static String createUpdateAllUserFields() {
		
		String queryParameter = "";
		
		for(String fieldName: userFieldNames) {
			queryParameter += querySyntax + fieldName + "&";
		}
		
		queryParameter = queryParameter.substring(0, queryParameter.length() - 1);
		return queryParameter;
	}
}

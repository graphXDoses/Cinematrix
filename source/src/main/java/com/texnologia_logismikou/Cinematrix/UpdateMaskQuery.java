package com.texnologia_logismikou.Cinematrix;

public class UpdateMaskQuery {

	private static final String querySyntax = "updateMask.fieldPaths=";
	private static final String[] movieFieldNames = {"title", "releaseYear", "mpRating", "duration"
			, "ytTrailerUrl", "categories", "cinemas", "description", "director"};

	public static String createUpdateAllMovieFieldsQuery() {
		
		String queryParameter = "";
		
		for(String fieldName: movieFieldNames) {
			
			queryParameter += querySyntax + fieldName + "&";
		}
		
		queryParameter = queryParameter.substring(0, queryParameter.length() - 1);
		return queryParameter;
	}
}

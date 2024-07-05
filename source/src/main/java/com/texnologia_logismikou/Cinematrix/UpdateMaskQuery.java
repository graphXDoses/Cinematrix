package com.texnologia_logismikou.Cinematrix;

public class UpdateMaskQuery {

	private static final String querySyntax = "updateMask.fieldPaths=";
	public static final String[] movieFieldNames = {"title", "releaseYear", "mpRating", "duration"
			, "ytTrailerUrl", "categories", "cinemas", "description", "director"};

	public static final String[] userFieldNames = {"admin", "email", "name"};
	
	public static final String[] roomFieldNames = {"screenings", "roomId"};
	
	public static String createUpdateAllFieldsQuery(String[] fieldNames) {
		
		String queryParameter = "";
		
		for(String fieldName: fieldNames) {
			queryParameter += querySyntax + fieldName + "&";
		}
		
		queryParameter = queryParameter.substring(0, queryParameter.length() - 1);
		return queryParameter;
	}
}

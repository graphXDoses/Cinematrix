package com.texnologia_logismikou.Cinematrix;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.Fields;

public class UpdateMaskQuery {

	private static final String querySyntax = "updateMask.fieldPaths=";

	public static String createUpdateAllFieldsQuery(Fields fields) {
		
		String queryParameter = "";
		
		for(String fieldName: fields.getFieldNames()) {
			
			queryParameter += querySyntax + fieldName;
		}
		
		return queryParameter;
	}
}

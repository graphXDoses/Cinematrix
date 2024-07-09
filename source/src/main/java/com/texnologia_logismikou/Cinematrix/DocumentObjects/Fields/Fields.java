package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

import java.lang.reflect.Field;

public class Fields {

	public String createQueryParameter() {
		
		String querySyntax = "updateMask.fieldPaths=";
		String query = "";
		
		for(Field i: this.getClass().getDeclaredFields()) {
			
			query += querySyntax + i.getName() + "&";
		}
		query = query.substring(0, query.length() - 1);
		return query;
	}
}

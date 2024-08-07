package com.texnologia_logismikou.Cinematrix.DocumentObjects;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.ScreeningFields;
import com.texnologia_logismikou.Cinematrix.ResponseBodies.ErrorResponseBody;

public class ScreeningDocument {

	private ErrorResponseBody error;
	
	private String name;
	private String createTime;
	private String updateTime;
	private ScreeningFields fields;
	
	public ErrorResponseBody getError() {
		return error;
	}
	public void setError(ErrorResponseBody error) {
		this.error = error;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public ScreeningFields getFields() {
		return fields;
	}
	public void setFields(ScreeningFields fields) {
		this.fields = fields;
	}
}

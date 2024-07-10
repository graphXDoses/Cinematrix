package com.texnologia_logismikou.Cinematrix.DocumentObjects;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.CinemaFields;
import com.texnologia_logismikou.Cinematrix.ResponseBodies.ErrorResponseBody;

public class CinemaDocument {

	private ErrorResponseBody error;
	
	private String name;
	private CinemaFields fields;
	private String createTime;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CinemaFields getFields() {
		return fields;
	}
	public void setFields(CinemaFields fields) {
		this.fields = fields;
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
	private String updateTime;

	public ErrorResponseBody getError() {
		return error;
	}
	public void setError(ErrorResponseBody error) {
		this.error = error;
	}
}

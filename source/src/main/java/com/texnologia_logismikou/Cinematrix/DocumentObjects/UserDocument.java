package com.texnologia_logismikou.Cinematrix.DocumentObjects;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.UserFields;

public class UserDocument {

	private String name;
	private String createTime;
	private String updateTime;
	private UserFields fields;
	
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
	public UserFields getFields() {
		return fields;
	}
	public void setFields(UserFields fields) {
		this.fields = fields;
	}
}

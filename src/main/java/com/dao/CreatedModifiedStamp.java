package com.dao;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

// TODO: clean this

@SuppressWarnings("serial")
public abstract class CreatedModifiedStamp extends UUIDGen {
	@CreatedDate
	private Date created;
	@LastModifiedDate
	private Date modified;
	private String creator;
	private Map<Principal, Date> modifier;
	// TODO: ADD SPRING SECURITY TO PROJECT
	@Autowired
	private Principal principal;

	protected CreatedModifiedStamp() {

		String user = "demo user";
		setCreated();
		setCreator(user);
		this.modifier = new HashMap<>();

	}

	public void setModifierData(String user) {
		setModified();
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated() {
		this.created = new Date();
	}

	public Date getModified() {
		return modified;
	}

	public void setModified() {
		this.modifier.put(this.principal, new Date());
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String user) {

	}

	@Override

	public boolean isNew() {
		return false;
	}

}

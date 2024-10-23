package com.dao;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;

import lombok.*;

@Setter
public abstract class UUIDGen implements Persistable<String> {

	@Id
	private String id;

	public UUIDGen() {
		this.id = UUID.randomUUID().toString();
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public boolean isNew() {
		return false;
	}
}
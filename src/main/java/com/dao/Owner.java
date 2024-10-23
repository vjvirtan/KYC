package com.dao;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
public class Owner extends UUIDGen {
	private String personId;
	private float percentage;

}

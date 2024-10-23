package com.dao;

import java.util.*;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class KYC extends CreatedModifiedStamp {
	private String companyId;
	private Set<String> beneficialBeneficiaries;
	private Map<String, Double> owners;
	private Map<String, Set<BoardMember>> members;
	private Map<String, List<Representative>> representatives;

	public KYC(String companyId) {
		this.companyId = companyId;
		owners = new HashMap<>();
		members = new HashMap<>();

	}

}

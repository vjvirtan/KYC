package com.demoData;

import java.util.*;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CompanyDto(String businessId, String personId, String id, ArrayList<String> info) {

}

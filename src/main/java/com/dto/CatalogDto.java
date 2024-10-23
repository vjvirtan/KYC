package com.dto;

import java.util.*;

import lombok.*;

@Builder
public record CatalogDto<T>(String name, List<T> items) {

}

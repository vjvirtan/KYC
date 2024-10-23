package com.dao;

import org.springframework.data.mongodb.core.mapping.*;

import com.annotation.*;

import lombok.*;


@Document
@Builder
@Getter
public class BeneficialOwner extends UUIDGen {

  private String personId;

}

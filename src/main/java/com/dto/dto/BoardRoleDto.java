package com.dto.dto;

import com.interfece.*;

import lombok.*;

/**
 * BoardRolesDto
 */

@Builder
public record BoardRoleDto(String personId, String boardRole)
        implements RuledDtoInterface {

}
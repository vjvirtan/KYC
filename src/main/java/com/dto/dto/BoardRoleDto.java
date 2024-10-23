package com.dto.dto;

import com.interfece.*;

import lombok.*;

/**
 * BoardRolesDto
 */

@Builder
public record BoardRoleDto(String id, String personId, String boardRole)
        implements RuledDtoInterface {

}
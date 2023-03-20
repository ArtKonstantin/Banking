package org.aston.credit.dto.responses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ValidationErrorResponse {

    private final List<ViolationDto> violationDtos;

}


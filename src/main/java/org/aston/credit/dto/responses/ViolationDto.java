package org.aston.credit.dto.responses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ViolationDto {

    private final String fieldName;
    private final String message;

}

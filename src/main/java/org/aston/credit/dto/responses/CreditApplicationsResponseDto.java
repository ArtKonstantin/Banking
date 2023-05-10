package org.aston.credit.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditApplicationsResponseDto {
    private List<CreditOrderResponseDto> creditApplications;
}


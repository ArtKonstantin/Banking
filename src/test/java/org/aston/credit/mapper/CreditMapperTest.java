package org.aston.credit.mapper;

import org.aston.credit.dto.ScheduleResponseDto;
import org.aston.credit.helper.CreditHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditMapperTest {
    final CreditMapperImpl creditMapper = new CreditMapperImpl();

    @Test
    void toDto() {
        ScheduleResponseDto schedule = creditMapper.toDto(CreditHelper.getCredit());
        final ScheduleResponseDto scheduleDto = CreditHelper.getScheduleDto();

        assertEquals(scheduleDto.getAccountNumber(), schedule.getAccountNumber());
        assertEquals(scheduleDto.getAgreementId(), schedule.getAgreementId());
        assertEquals(scheduleDto.getCurrentPrincipalAmount(), schedule.getCurrentPrincipalAmount());
        assertEquals(scheduleDto.getCurrentInterestAmount(), schedule.getCurrentInterestAmount());
        assertEquals(scheduleDto.getPayments(), schedule.getPayments());
    }
}

package org.aston.credit.controller;

import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.ScheduleResponseDto;
import org.aston.credit.entity.CreditEntity;
import org.aston.credit.mapper.CreditMapper;
import org.aston.credit.service.CreditService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/credits")
public class CreditController {
    private final CreditService creditService;
    private final CreditMapper creditMapper;

    @GetMapping("/{creditId}/schedule")
    public ScheduleResponseDto schedule(@RequestHeader UUID clientId, @PathVariable long creditId) {
        CreditEntity credit = creditService.schedule(clientId, creditId);
        ScheduleResponseDto schedule = creditMapper.toDto(credit);
        return schedule;
    }
}

package org.aston.credit.controller;

import lombok.RequiredArgsConstructor;
import org.aston.credit.entity.OperationEntity;
import org.aston.credit.service.OperationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/operation")
public class OperationController {
    private final OperationService operationService;

    @GetMapping
    private List<OperationEntity> getAll() {
        return operationService.getAll();
    }
}

package org.aston.credit.service;

import lombok.RequiredArgsConstructor;
import org.aston.credit.entity.OperationEntity;
import org.aston.credit.repository.OperationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationService {
    private final OperationRepository operationRepository;

    public List<OperationEntity> getAll() {
        return operationRepository.findAll();
    }
}

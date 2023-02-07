package org.aston.credit.service;

import org.aston.credit.entity.OperationEntity;
import org.aston.credit.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationService {
    private final OperationRepository operationRepository;

    @Autowired
    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public List<OperationEntity> getAll() {
        return operationRepository.findAll();
    }
}

package org.aston.credit.service;

import lombok.RequiredArgsConstructor;
import org.aston.credit.entity.CreditEntity;
import org.aston.credit.exception.ForbiddenException;
import org.aston.credit.repository.CreditRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditService {
    private final CreditRepository creditRepository;

    public List<CreditEntity> getAll() {
        return creditRepository.findAll();
    }

    public CreditEntity schedule(UUID clientId, long creditId) {
        final CreditEntity credit = creditRepository.getReferenceById(creditId);

        if (!clientId.equals(credit.getCreditOrder().getClientId())) {
            throw new ForbiddenException();
        }

        return credit;
    }
}

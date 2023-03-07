package org.aston.credit.repository;

import org.aston.credit.entity.CreditProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditProductRepository extends JpaRepository<CreditProductEntity, Long> {

    List<CreditProductEntity> findAllByProductIsActiveIsTrue();

}

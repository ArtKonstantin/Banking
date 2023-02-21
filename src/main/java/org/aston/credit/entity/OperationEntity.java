package org.aston.credit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "operation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OperationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_number", referencedColumnName = "account_number", nullable = false)
    private CreditAccountEntity creditAccount;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private OperationTypeEnum type;

    @Column(name = "sum", nullable = false)
    private BigDecimal sum;

    @Column(name = "date", nullable = false)
    private Instant date;

    @Column(name = "details")
    private String details;

    @Column(name = "currency_code", nullable = false)
    private String currencyCode;
}

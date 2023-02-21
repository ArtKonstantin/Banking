package org.aston.credit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "credit")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(mappedBy = "credit")
    private CreditAgreementEntity creditAgreement;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private CreditOrderEntity creditOrder;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CreditTypeEnum type;

    @Column(name = "credit_limit", nullable = false)
    private BigDecimal creditLimit;

    @Column(name = "currency_code", nullable = false)
    private String currencyCode;

    @Column(name = "interest_rate", nullable = false)
    private BigDecimal interestRate;

    @Column(name = "personal_guarantees", nullable = false)
    private boolean personalGuarantees;

    @Column(name = "grace_period_months", nullable = false)
    private int gracePeriodMonths;

    @Column(name = "credit_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CreditStatusEnum creditStatus;

    @OneToOne
    @JoinColumn(name = "account_number", referencedColumnName = "account_number", nullable = false)
    private CreditAccountEntity creditAccount;
}

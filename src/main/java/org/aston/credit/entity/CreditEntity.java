package org.aston.credit.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aston.credit.entity.enums.CreditStatusEnum;
import org.aston.credit.entity.enums.CreditTypeEnum;

import java.math.BigDecimal;

@Entity
@Table(name = "credit")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditEntity {
    @Id
    private long id;
    @OneToOne(mappedBy = "credit", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private CreditAgreementEntity creditAgreement;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
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
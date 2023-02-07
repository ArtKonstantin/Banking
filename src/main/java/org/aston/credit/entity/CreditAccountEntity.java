package org.aston.credit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "credit_account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditAccountEntity {
    @Id
    @Column (name = "account_number")
    private String accountNumber;

    @JsonIgnore
    @OneToOne(mappedBy = "creditAccount")
    private CreditEntity credit;

    @OneToMany(mappedBy = "creditAccount")
    private List<PaymentScheduleEntity> paymentSchedule;

    @OneToMany(mappedBy = "creditAccount")
    private List<CreditCardEntity> creditCard;

    @OneToMany(mappedBy = "creditAccount")
    private List<OperationEntity> operation;

    @Column(name = "principal_debt", nullable = false)
    private BigDecimal principalDebt;

    @Column(name = "interest_debt", nullable = false)
    private BigDecimal interestDebt;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "opening_date", nullable = false)
    private Date openingDate;

    @Column(name = "closing_date", nullable = false)
    private Date closingDate;

    @Column(name = "currency_code", nullable = false)
    private String currencyCode;
}

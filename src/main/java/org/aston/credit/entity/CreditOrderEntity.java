package org.aston.credit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aston.credit.entity.enums.OrderStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "credit_application")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @Column(name = "client_id", nullable = false)
    private UUID clientId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private CreditEntity credit;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private CreditProductEntity creditProduct;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "period_months", nullable = false)
    private int periodMonths;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(name = "average_monthly_income", nullable = false)
    private BigDecimal averageMonthlyIncome;

    @Column(name = "average_monthly_expenditure", nullable = false)
    private BigDecimal averageMonthlyExpenditure;

    @Column(name = "employer_identification_number", nullable = false)
    private String employerIdentificationNumber;
}

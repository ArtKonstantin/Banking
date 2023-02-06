package org.aston.credit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "credit_order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(mappedBy = "creditOrder")
    private CreditEntity credit;

    @Column(name = "client_id", nullable = false)
    private UUID clientId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private CreditProductEntity creditProduct;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "period_months", nullable = false)
    private int periodMonths;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "average_monthly_income", nullable = false)
    private BigDecimal averageMonthlyIncome;

    @Column(name = "average_monthly_expenditure", nullable = false)
    private BigDecimal averageMonthlyExpenditure;

    @Column(name = "employer_identification_number", nullable = false)
    private String employerIdentificationNumber;
}

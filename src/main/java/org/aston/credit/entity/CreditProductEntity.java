package org.aston.credit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "credit_product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @OneToMany(mappedBy = "creditProduct")
    private List<CreditOrderEntity> creditOrders;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "min_sum", nullable = false)
    private BigDecimal minSum;

    @Column(name = "max_sum", nullable = false)

    private BigDecimal maxSum;

    @Column(name = "currency_code", nullable = false)
    private String currencyCode;

    @Column(name = "min_interest_rate", nullable = false)
    private BigDecimal minInterestRate;

    @Column(name = "max_interest_rate", nullable = false)
    private BigDecimal maxInterestRate;

    @Column(name = "need_guarantees", nullable = false)
    private boolean needGuarantees;

    @Column(name = "delivery_in_cash", nullable = false)
    private boolean deliveryInCash;

    @Column(name = "early_repayment", nullable = false)
    private boolean earlyRepayment;

    @Column(name = "min_period_months", nullable = false)
    private int minPeriodMonths;

    @Column(name = "max_period_months", nullable = false)
    private int maxPeriodMonths;

    @Column(name = "product_is_active", nullable = false)
    private boolean productIsActive;

    @Column(name = "product_details", nullable = false)
    private String productDetails;

    @Column(name = "calculation_mode", nullable = false)
    @Enumerated(EnumType.STRING)
    private CalculationModeEnum calculationMode;

    @Column(name = "grace_period_months", nullable = false)
    private int gracePeriodMonths;

    @Column(name = "need_income_details", nullable = false)
    private boolean needIncomeDetails;
}

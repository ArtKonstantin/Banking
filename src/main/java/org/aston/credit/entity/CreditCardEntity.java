package org.aston.credit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aston.credit.entity.enums.CardStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "credit_card")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditCardEntity {
    @Id
    @Column(name = "card_number")
    private String cardNumber;

    @ManyToOne
    @JoinColumn(name = "account_number", referencedColumnName = "account_number", nullable = false)
    @JsonIgnore
    private CreditAccountEntity creditAccount;

    @Column(name = "holder_name", nullable = false)
    private String holderName;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "payment_system", nullable = false)
    private String paymentSystem;

    @Column(name = "card_balance", nullable = false)
    private int cardBalance;

    @Column(name = "card_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CardStatusEnum cardStatus;

    @Column(name = "transaction_limit", nullable = false)
    private int transactionLimit;

    @Column(name = "delivery_point", nullable = false)
    private String deliveryPoint;

    @Column(name = "pin", nullable = false)
    private String pin;

    @Column(name = "credit_limit", nullable = false)
    private BigDecimal limit;
}

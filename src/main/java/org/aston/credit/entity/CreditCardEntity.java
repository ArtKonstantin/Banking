package org.aston.credit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

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
    private CreditAccountEntity creditAccount;

    @Column(name = "holder_name", nullable = false)
    private String holderName;

    @Column(name = "expiration_date", nullable = false)
    private Date expirationDate;

    @Column(name = "payment_system", nullable = false)
    private String paymentSystem;

    @Column(name = "card_balance", nullable = false)
    private int cardBalance;

    @Column(name = "card_status", nullable = false)
    private String cardStatus;

    @Column(name = "transaction_limit", nullable = false)
    private int transactionLimit;

    @Column(name = "delivery_point", nullable = false)
    private String deliveryPoint;
}

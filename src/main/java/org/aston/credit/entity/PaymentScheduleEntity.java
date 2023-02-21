package org.aston.credit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import java.sql.Date;

@Entity
@Table(name = "payment_schedule")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_number", referencedColumnName = "account_number", nullable = false)
    private CreditAccountEntity creditAccount;

    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;

    @Column(name = "principal", nullable = false)
    private BigDecimal principal;

    @Column(name = "interest", nullable = false)
    private BigDecimal interest;
}

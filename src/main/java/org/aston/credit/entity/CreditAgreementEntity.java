package org.aston.credit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "credit_agreement")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditAgreementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "credit_id", referencedColumnName = "id", nullable = false)
    private CreditEntity credit;

    @Column(name = "agreement_date", nullable = false)
    private Date agreementDate;

    @Column(name = "termination_date", nullable = false)
    private Date terminationDate;

    @Column(name = "responsible_specialist_id")
    private long responsibleSpecialistId;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
}

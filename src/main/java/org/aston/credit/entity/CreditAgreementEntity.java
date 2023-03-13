package org.aston.credit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

import java.time.LocalDate;

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
    @JsonIgnore
    @JoinColumn(name = "credit_id", referencedColumnName = "id", nullable = false)
    private CreditEntity credit;

    @Column(name = "agreement_date", nullable = false)
    private LocalDate agreementDate;

    @Column(name = "termination_date", nullable = false)
    private LocalDate terminationDate;

    @Column(name = "responsible_specialist_id")
    private long responsibleSpecialistId;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
}

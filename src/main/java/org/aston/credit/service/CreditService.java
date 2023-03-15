package org.aston.credit.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aston.credit.entity.CreditAccountEntity;
import org.aston.credit.entity.CreditEntity;
import org.aston.credit.entity.PaymentScheduleEntity;
import org.aston.credit.exception.ForbiddenException;
import org.aston.credit.repository.CreditRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditService {
    private final CreditRepository creditRepository;

    public CreditEntity schedule(UUID clientId, long creditId) {
        final CreditEntity credit = creditRepository.getReferenceById(creditId);

        if (!clientId.equals(credit.getCreditOrder().getClientId())) {
            throw new ForbiddenException();
        }

        return credit;
    }

    public CreditEntity credit(long agreementId) {
        final CreditEntity credit = creditRepository.findByCreditAgreementId(agreementId);

        if (credit == null) {
            throw new EntityNotFoundException();
        }

        return credit;
    }

    public PaymentScheduleEntity payment(List<PaymentScheduleEntity> schedule) {
        return scheduleAfter(schedule).get(0);
    }

    public CreditAccountEntity debt(CreditAccountEntity creditAccount) {
        BigDecimal principalDebt = creditAccount.getPrincipalDebt();
        BigDecimal interestDebt = creditAccount.getInterestDebt();
        List<PaymentScheduleEntity> schedule = scheduleBefore(creditAccount.getPaymentSchedule());

        for (PaymentScheduleEntity pay : schedule) {
            principalDebt = principalDebt.subtract(pay.getPrincipal());
            interestDebt = interestDebt.subtract(pay.getInterest());
        }

        creditAccount.setPrincipalDebt(principalDebt);
        creditAccount.setInterestDebt(interestDebt);

        return creditAccount;
    }

    public List<PaymentScheduleEntity> scheduleAfter(List<PaymentScheduleEntity> paymentSchedule) {
        List<PaymentScheduleEntity> schedule = new ArrayList<>();
        final LocalDate date = LocalDate.now();

        for (PaymentScheduleEntity pay : paymentSchedule) {
            if (pay.getPaymentDate().isAfter(date)) {
                schedule.add(pay);
            }
        }

        return schedule;
    }

    public List<PaymentScheduleEntity> scheduleBefore(List<PaymentScheduleEntity> paymentSchedule) {
        List<PaymentScheduleEntity> schedule = new ArrayList<>();
        final LocalDate date = LocalDate.now();

        for (PaymentScheduleEntity pay : paymentSchedule) {
            if (pay.getPaymentDate().isBefore(date)) {
                schedule.add(pay);
            }
        }

        return schedule;
    }

    /**
     * CR.2 - Отправка полной информации о кредитном продукте клиента.
     * <p>
     * Запрос на получение подробной информации о действующем кредитном продукте пользователя из БД.
     * <p>
     * Происходит SELECT запрос в БД на поиск информации о кредите по его id, если такой id есть -
     * возвращается вся информация из БД по кредиту для передачи её в контроллер и последующего маппинга в ДТО,
     * если её нет - выбрасывыается исключение
     * <p>
     * Следующие поля не включены в маппинг: number (номер кредитного договра), payment date, три неописанных поля.
     *
     * @param creditId id крединтного продукта
     * @return CreditEntity с информацией о кредитных продуктах клиента
     * @throws java.util.NoSuchElementException если клиента с таким id не существует
     */
    public CreditEntity getInformation(Long creditId) {
        return creditRepository.findById(creditId).orElseThrow();
    }
}

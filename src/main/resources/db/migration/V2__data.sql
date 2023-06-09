INSERT INTO credit_product(name, min_sum, max_sum, currency_code, min_interest_rate, max_interest_rate,
                           need_guarantees, delivery_in_cash, early_repayment, min_period_months, max_period_months,
                           is_active, details, calculation_mode, grace_period_months,
                           need_income_details, interest_rate)
VALUES ('Стартовый', 1000.00, 1000000.00, 'RUB', 5.00, 30.00, false, false, true, 6, 36, true, 'Стандартный кредит',
        'ANNUITY', 0, true, 20.00);

INSERT INTO account(account_number, principal_debt, interest_debt, is_active, opening_date, closing_date,
                    currency_code, current_principal_amount, current_interest_amount)
VALUES ('10000000000000000001', 980000.00, 39010.00, true, '10.01.2022', '10.01.2025', 'RUB', 980000.00, 39010.00);

INSERT INTO credit_application(client_id, number, product_id, status, amount, period_months,
                               creation_date, average_monthly_income, average_monthly_expenditure,
                               employer_identification_number)
VALUES ('0799f8b8-729d-4818-b1ba-5e64f88f6d03', '1234567', 1, 'APPROVED_BY_BANK', 900000.00, 6, '01.01.2022', 100000.00,
        50000.00,
        '123456789012');

INSERT INTO card(id, card_number, account_number, holder_name, expiration_date, payment_system, card_balance,
                 card_status, transaction_limit, delivery_point, pin, credit_limit)
VALUES ('f34f8980-0965-4132-8947-deb1e6170b2c', '1234567891234567', '10000000000000000001', 'TEST TESTOVICH',
        '10.01.2025', 'VISA', 100000, 'ACTIVE', 10000,
        'Kazan Sovetskiy', '1234', 900000.00);

INSERT INTO credit(id, type, credit_limit, currency_code, interest_rate,
                   status, principal_amount, interest_amount, account_number)
VALUES (1, 'CONSUMER_CREDIT', 900000.00, 'RUB', 15.00, 'ACTIVE', 900000.00, 39010.00, '10000000000000000001');

INSERT INTO agreement(id, agreement_date, termination_date, responsible_specialist_id, is_active, number)
VALUES (1, '10.01.2022', '10.01.2025', 123, true, '1234567890qwertyuiop');

INSERT INTO payment_schedule(account_number, payment_date, principal, interest)
VALUES ('10000000000000000001', '2023-01-02', 146274.27, 10356.16);

INSERT INTO payment_schedule(account_number, payment_date, principal, interest)
VALUES ('10000000000000000001', '2023-02-02', 147028.17, 9602.26);

INSERT INTO payment_schedule(account_number, payment_date, principal, interest)
VALUES ('10000000000000000001', '2023-03-02', 149150.60, 7479.83);

INSERT INTO payment_schedule(account_number, payment_date, principal, interest)
VALUES ('10000000000000000001', '2023-04-02', 150801.41, 5829.02);

INSERT INTO payment_schedule(account_number, payment_date, principal, interest)
VALUES ('10000000000000000001', '2023-05-02', 152848.64, 3781.79);

INSERT INTO payment_schedule(account_number, payment_date, principal, interest)
VALUES ('10000000000000000001', '2023-06-02', 153896.91, 1960.60);

INSERT INTO operation(account_number, type, sum, details, currency_code)
VALUES ('10000000000000000001', 'EXPENSE', 10000.00, 'покупка', 'RUB');
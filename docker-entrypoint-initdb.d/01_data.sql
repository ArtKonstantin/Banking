INSERT INTO credit_product(product_name, min_sum, max_sum, currency_code, min_interest_rate, max_interest_rate,
                           need_guarantees, delivery_in_cash, early_repayment, min_period_months, max_period_months,
                           product_is_active, product_details, calculation_mode, grace_period_months,
                           need_income_details)
VALUES ('Стартовый', 1000.00, 1000000.00, 'RUB', 0.05, 0.30, false, false, true, 6, 36, true, 'Стандартный кредит',
        'differentiated', 0, true);

INSERT INTO credit_account(account_number, principal_debt, interest_debt, is_active, opening_date, closing_date,
                           currency_code)
VALUES ('10000000000000000001', 980000.50, 330120.00, true, '10.01.2022', NULL, 'RUB');

INSERT INTO credit_order(client_id, product_id, status, amount, period_months,
                         creation_date, average_monthly_income, average_monthly_expenditure,
                         employer_identification_number)
VALUES ('0799f8b8-729d-4818-b1ba-5e64f88f6d03', 1, 'approved', 900000.00, 12, '01.01.2022', 100000.00, 50000.00,
        '123456789012');

INSERT INTO credit_card(card_number, account_number, holder_name, expiration_date, payment_system, card_balance,
                        card_status, transaction_limit, delivery_point)
VALUES ('1234567891234567', '10000000000000000001', 'TEST TESTOVICH', '10.01.2025', 'VISA', 100000, 'OK', 10000,
        'Kazan Sovetskiy');
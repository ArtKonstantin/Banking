CREATE TABLE credit_product
(
    id                  BIGSERIAL PRIMARY KEY,
    product_name        VARCHAR(50)    NOT NULL,
    min_sum             NUMERIC(19, 2) NOT NULL,
    max_sum             NUMERIC(19, 2) NOT NULL,
    currency_code       VARCHAR(3)     NOT NULL,
    min_interest_rate   NUMERIC(2, 2)  NOT NULL,
    max_interest_rate   NUMERIC(2, 2)  NOT NULL,
    need_guarantees     BOOLEAN        NOT NULL,
    delivery_in_cash    BOOLEAN        NOT NULL,
    early_repayment     BOOLEAN        NOT NULL,
    min_period_months   INT            NOT NULL,
    max_period_months   INT            NOT NULL,
    product_is_active   BOOLEAN        NOT NULL,
    product_details     TEXT           NOT NULL,
    calculation_mode    VARCHAR(30)    NOT NULL,
    grace_period_months INT            NOT NULL,
    need_income_details BOOLEAN        NOT NULL
);

CREATE TABLE credit_account
(
    account_number VARCHAR(20) PRIMARY KEY,
    principal_debt NUMERIC(19, 2) NOT NULL,
    interest_debt  NUMERIC(19, 2) NOT NULL,
    is_active      BOOLEAN        NOT NULL,
    opening_date   DATE           NOT NULL,
    closing_date   DATE,
    currency_code  VARCHAR(3)     NOT NULL
);

CREATE TABLE credit_order
(
    id                             BIGSERIAL PRIMARY KEY,
    client_id                      UUID           NOT NULL,
    product_id                     BIGINT         NOT NULL REFERENCES credit_product,
    status                         VARCHAR(30)    NOT NULL,
    amount                         NUMERIC(19, 2) NOT NULL,
    period_months                  INT            NOT NULL,
    creation_date                  DATE           NOT NULL,
    average_monthly_income         NUMERIC(19, 2) NOT NULL,
    average_monthly_expenditure    NUMERIC(19, 2) NOT NULL,
    employer_identification_number VARCHAR(12)    NOT NULL
);

CREATE TABLE credit
(
    id                  BIGSERIAL PRIMARY KEY,
    order_id            BIGINT         NOT NULL REFERENCES credit_order,
    type                VARCHAR(30)    NOT NULL,
    credit_limit        NUMERIC(19, 2) NOT NULL,
    currency_code       VARCHAR(3)     NOT NULL,
    interest_rate       NUMERIC(2, 2)  NOT NULL,
    personal_guarantees BOOLEAN        NOT NULL,
    grace_period_months INT            NOT NULL,
    credit_status       VARCHAR(30)    NOT NULL,
    account_number      VARCHAR(20)    NOT NULL REFERENCES credit_account
);

CREATE TABLE credit_agreement
(
    id                        BIGSERIAL PRIMARY KEY,
    credit_id                 BIGINT  NOT NULL REFERENCES credit,
    agreement_date            DATE    NOT NULL,
    termination_date          DATE,
    responsible_specialist_id BIGINT,
    is_active                 BOOLEAN NOT NULL
);

CREATE TABLE payment_schedule
(
    id             BIGSERIAL PRIMARY KEY,
    account_number VARCHAR(20)    NOT NULL REFERENCES credit_account,
    payment_date   DATE           NOT NULL,
    principal      NUMERIC(19, 2) NOT NULL,
    interest       NUMERIC(19, 2) NOT NULL
);

CREATE TABLE credit_card
(
    card_number       VARCHAR(16) PRIMARY KEY,
    account_number    VARCHAR(20) NOT NULL REFERENCES credit_account,
    holder_name       VARCHAR(50) NOT NULL,
    expiration_date   DATE        NOT NULL,
    payment_system    VARCHAR(20) NOT NULL,
    card_balance      INT         NOT NULL,
    card_status       VARCHAR(50) NOT NULL,
    transaction_limit INT         NOT NULL,
    delivery_point    VARCHAR(50) NOT NULL
);

CREATE TABLE operation
(
    id             BIGSERIAL PRIMARY KEY,
    account_number VARCHAR(20)    NOT NULL REFERENCES credit_account,
    type           VARCHAR(30)    NOT NULL,
    sum            NUMERIC(19, 2) NOT NULL,
    date           TIMESTAMPTZ    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    details        TEXT,
    currency_code  VARCHAR(3)     NOT NULL
);
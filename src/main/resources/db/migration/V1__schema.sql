CREATE TABLE credit_product
(
    id                  BIGSERIAL PRIMARY KEY,
    name                VARCHAR(50)    NOT NULL,
    min_sum             NUMERIC(14, 2) NOT NULL,
    max_sum             NUMERIC(14, 2) NOT NULL,
    currency_code       VARCHAR(3)     NOT NULL,
    min_interest_rate   NUMERIC(5, 2)  NOT NULL,
    max_interest_rate   NUMERIC(5, 2)  NOT NULL,
    need_guarantees     BOOLEAN        NOT NULL,
    delivery_in_cash    BOOLEAN        NOT NULL,
    early_repayment     BOOLEAN        NOT NULL,
    min_period_months   INT            NOT NULL,
    max_period_months   INT            NOT NULL,
    is_active           BOOLEAN        NOT NULL,
    details             TEXT           NOT NULL,
    calculation_mode    VARCHAR(30)    NOT NULL,
    grace_period_months INT            NOT NULL,
    need_income_details BOOLEAN        NOT NULL,
    interest_rate       NUMERIC(5, 2)  NOT NULL
);

create table if not exists account
(
    account_number           varchar(20)    not null
        primary key,
    principal_debt           numeric(14, 2) not null,
    interest_debt            numeric(14, 2) not null,
    is_active                boolean        not null,
    opening_date             date           not null,
    closing_date             date           not null,
    currency_code            varchar(3)     not null,
    current_principal_amount numeric(14, 2) not null,
    current_interest_amount  numeric(14, 2) not null
);

create table if not exists credit_application
(
    id                             bigserial
        primary key,
    client_id                      UUID           not null,
    product_id                     bigint         not null
        constraint credit_product_product_id_fk
            references credit_product,
    status                         varchar(30)    not null,
    amount                         numeric(14, 2) not null,
    period_months                  integer        not null,
    average_monthly_expenditure    numeric(14, 2) not null,
    creation_date                  date           not null,
    average_monthly_income         numeric(14, 2) not null,
    employer_identification_number varchar(12)    not null

);

create table if not exists credit
(
    id               bigint
        primary key
        constraint credit_application_id_fk
            references credit_application,
    type             varchar(30)    not null,
    credit_limit     numeric(14, 2) not null,
    currency_code    varchar(3)     not null,
    interest_rate    numeric(5, 2)  not null,
    status           varchar(30)    not null,
    principal_amount numeric(14, 2) not null,
    interest_amount  numeric(14, 2) not null,
    account_number   varchar(20)    not null
        constraint account_account_number_fk
            references account
);

create table if not exists agreement
(
    id                        bigint             not null
        primary key
        constraint credit_id_fk
            references credit,
    agreement_date            date               not null,
    termination_date          date               not null,
    responsible_specialist_id bigint,
    is_active                 boolean            not null,
    number                    varchar(20) unique not null
);

CREATE TABLE payment_schedule
(
    id             BIGSERIAL PRIMARY KEY,
    account_number VARCHAR(20)    NOT NULL REFERENCES account,
    payment_date   DATE           NOT NULL,
    principal      NUMERIC(19, 2) NOT NULL,
    interest       NUMERIC(19, 2) NOT NULL
);

CREATE TABLE card
(
    id                UUID PRIMARY KEY,
    card_number       VARCHAR(20)    NOT NULL,
    account_number    VARCHAR(20)    NOT NULL REFERENCES account,
    holder_name       VARCHAR(50)    NOT NULL,
    expiration_date   DATE           NOT NULL,
    payment_system    VARCHAR(20)    NOT NULL,
    card_balance      INT            NOT NULL,
    card_status       VARCHAR(50)    NOT NULL,
    transaction_limit INT            NOT NULL,
    delivery_point    VARCHAR(50)    NOT NULL,
    pin               VARCHAR(4)     NOT NULL,
    credit_limit      NUMERIC(19, 4) NOT NULL
);

CREATE TABLE operation
(
    id             BIGSERIAL PRIMARY KEY,
    account_number VARCHAR(20)    NOT NULL REFERENCES account,
    type           VARCHAR(30)    NOT NULL,
    sum            NUMERIC(19, 2) NOT NULL,
    date           TIMESTAMPTZ    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    details        TEXT,
    currency_code  VARCHAR(3)     NOT NULL
);
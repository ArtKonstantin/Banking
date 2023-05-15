package org.aston.credit;

public class Constants {

    public static final String UUID = "uuid клиента или карты";

    public static final String UUID_PATTERN = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";

    public static final String UUID_BLANK = "uuid клиента не может быть пустым";

    public static final String UUID_INVALID = "введённый uuid не соответствует паттерну для uuid";

    public static final String CREDIT_ID = "id кредита";

    public static final String CREDIT_ID_BLANK = "id кредита не может быть пустым";

    public static final String AGREEMENT_ID = "id кредитного договора";

    public static final String AGREEMENT_ID_BLANK = "id кредитного договора не может быть пустым";

    public static final String AGREEMENT_ID_INVALID = "id кредитного договора не соответствует паттерну";

    public static final String CREDIT_CARD_INVALID = "номер кредитной карты не соответствует паттерну";

    public static final String CREDIT_CARD_BLANK = "номер кредитной карты не может быть пустым";

    public static final String EMPLOYER_IDENTIFICATION_NUMBER_PATTERN = "^[0-9]{12}$";

    public static final String CARD_NUMBER_PATTERN = "^[0-9]{16}$";

    public static final String PIN_CODE_PATTERN = "^[0-9]{4}$";

    public static final String FIELD_MUST_NOT_BE_BLANK = "поле не должно быть пустым";

    public static final String FIELD_MUST_BE_POSITIVE = "поле должно быть положительным";

    public static final String FIELD_MUST_NOT_BE_NEGATIVE = "поле не должно быть отрицательным";

    public static final String FIELD_NOT_EQUALS_PATTERN = "поле не соответствует паттерну";
}

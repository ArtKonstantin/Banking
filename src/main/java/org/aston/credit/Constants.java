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
}

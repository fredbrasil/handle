package com.br.havecontrol.common.enums;

public enum EnumPaymentType {

    CASH("H", "cash", 0),
    CARD("D", "card", 1),
    CHECK("K", "check", 2);

    private String code;
    private String name;
    private int id;

    private EnumPaymentType(String code, String name, int id) {
        this.code = code;
        this.name = name;
        this.id = id;
    }

    public static EnumPaymentType getPaymentType(String code) {

        if (code.equals(CASH.getCode())) {
            return CASH;
        } else if (code.equals(CHECK.getCode())) {
            return CHECK;
        } else {
            return CARD;
        }
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}

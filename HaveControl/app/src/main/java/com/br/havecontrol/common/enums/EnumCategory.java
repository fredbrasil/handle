package com.br.havecontrol.common.enums;

public enum EnumCategory {

    REVENUE("R", "Revenue"),
    EXPENSE("E", "Expense");

    private String code;
    private String name;

    private EnumCategory(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}

package com.br.havecontrol.common.enums;

public enum CostType {

    FIXED("F", "Fixed"),
    VARIED("V", "Varied");

    private String code;
    private String name;

    private CostType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static CostType getCostType(String code) {

        if (code.equals(FIXED.getCode())) {
            return FIXED;
        } else {
            return VARIED;
        }
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

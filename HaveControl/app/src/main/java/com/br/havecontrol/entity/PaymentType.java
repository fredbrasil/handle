package com.br.havecontrol.entity;

import com.br.havecontrol.common.enums.EnumPaymentType;

import java.io.Serializable;


/**
 * @author Fred
 */
public class PaymentType implements Serializable {

    public static final String PAYMENT_TYPE = "_PAYMENTTYPE";
    public static final String AMOUNT = "_AMOUNT";
    public static final String DATE_START_PAYMENT = "_DATESTARTPAYMENT";
    private static final long serialVersionUID = 1233937957253647233L;
    private EnumPaymentType paymentType;
    private boolean cash;
    private int amount;
    private String dateStartPayment;

    public EnumPaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(EnumPaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDateStartPayment() {
        return dateStartPayment;
    }

    public void setDateStartPayment(String dateStartPayment) {
        this.dateStartPayment = dateStartPayment;
    }

    public boolean isCash() {
        return cash;
    }

    public void setCash(boolean cash) {
        this.cash = cash;
    }

}

package com.in2it.cats.paymentservice.constant;

public final class PaymentConstants {

    private PaymentConstants() {
    }

    public static final String PAYMENT_NOT_FOUND = "Payment not found";
    public static final String VALIDATION_ERROR = "Validation failed";
    public static final String INTERNAL_SERVER_ERROR = "Internal server error";

    public static final String PAYMENT_PENDING = "PENDING";
    public static final String PAYMENT_SUCCESS = "SUCCESS";
    public static final String PAYMENT_FAILED = "FAILED";
}
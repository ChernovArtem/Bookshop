package ru.paymentservice.payments.services;

public class PaymentNotFoundException extends RuntimeException {

    PaymentNotFoundException(Long id) {
        super("payments not found" + id);
    }
}

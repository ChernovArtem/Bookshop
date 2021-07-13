package ru.paymentservice.payments.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaymentResult {

    private int checkNumber;
    private double amount;
    private String paymentStatus;
}


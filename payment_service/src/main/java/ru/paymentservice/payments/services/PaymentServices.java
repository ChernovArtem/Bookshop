package ru.paymentservice.payments.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.paymentservice.payments.models.PaymentBilling;

import java.util.List;

@Service
public class PaymentServices{

    @Autowired
    private PaymentServices paymentServices;

    public PaymentBilling save(PaymentBilling paymentBilling) {
        return paymentServices.save(paymentBilling);
    }

    public Iterable<PaymentBilling> save(List<PaymentBilling> paymentBilling){
        return paymentServices.save(paymentBilling);
    }
}

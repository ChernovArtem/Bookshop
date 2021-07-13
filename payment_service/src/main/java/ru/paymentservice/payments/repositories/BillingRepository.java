package ru.paymentservice.payments.repositories;

import ru.paymentservice.payments.models.PaymentBilling;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillingRepository extends JpaRepository<PaymentBilling, Long> {

    List<PaymentBilling> findAll();



}

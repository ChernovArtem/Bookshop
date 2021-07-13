package ru.inno.shop.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.inno.shop.model.order.Payment;

@Repository
public interface PaymentsDao extends JpaRepository<Payment, Long> {
}

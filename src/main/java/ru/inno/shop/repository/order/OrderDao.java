package ru.inno.shop.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inno.shop.model.order.Orders;
import java.util.Optional;
import java.util.Set;

/**
 * @author Артём Матюнин
 * Репозиторий заказа
 */

public interface OrderDao extends JpaRepository<Orders, Long> {

    /**
     * Поиск заказов клиента
     * @param customerId Id клиента
     * @return список заказов
     */
    Optional<Set<Orders>> getOrdersByCustomerId(Long customerId);
}

package ru.inno.shop.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inno.shop.model.order.OrderDetails;

/**
 * @author Артём Матюнин
 * Репозиторий списка товаров в заказе
 */

public interface OrderDetailDao extends JpaRepository<OrderDetails, Long> {

}

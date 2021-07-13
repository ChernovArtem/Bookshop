package ru.inno.shop.service.order.impl;

import org.springframework.stereotype.Component;
import ru.inno.shop.model.cart.Cart;
import ru.inno.shop.model.cart.CartDetails;
import ru.inno.shop.model.order.OrderDetails;
import ru.inno.shop.model.order.OrderStatus;
import ru.inno.shop.model.order.Orders;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

/**
 * @author Артём Матюнин
 * класс-маппер
 */

@Component
public class OrderMapper {

    /**
     * Подготовка нового заказа для записи в базу. Берет из корзины клиента и присваивает его заказу.
     * Присваивает заказу дату и статус
     *
     * @param cart Корзина
     * @return Подготовленный для записи в БД объект Orders (без Id)
     */
    public Orders prepareOrder(Cart cart) {
        Orders or = new Orders();
        or.setOrderDate(Date.from(Instant.now()));
        or.setCustomerId(cart.getCartCustomer());
        or.setStatus(OrderStatus.NEW_ORDER);
        return or;
    }

    /**
     * Мапит CartDetails в OrderDetails
     *
     * @param cartDetail Товар в корзине
     * @return Товар в заказе
     */
    public OrderDetails cartDetailToOrderDetail(Orders order, CartDetails cartDetail) {
        return new OrderDetails(
                order.getOrderNumber(),
                cartDetail.getCartDetailBook(),
                cartDetail.getBook(),
                order,
                cartDetail.getCartDetailQuantity(),
                BigDecimal.valueOf(cartDetail.getBook().getPrice())
        );
    }
}

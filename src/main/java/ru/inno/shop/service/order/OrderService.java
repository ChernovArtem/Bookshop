package ru.inno.shop.service.order;

import ru.inno.shop.model.cart.Cart;
import ru.inno.shop.model.customer.Customer;
import ru.inno.shop.model.order.Orders;
import java.util.Set;

/**
 * @author Артём Матюнин
 * Сервис заказа
 */

public interface OrderService {

    /**
     * Создание заказа
     * @param cart сформированный объект заказа, без Id
     * @return заказ с Id
     */
    Orders createOrder(Cart cart);

    /**
     * Поиск заказа по Id
     * @param orderId Номер заказа
     * @return Найденный заказ
     */
    Orders getOrderById(long orderId);

    /**
     * Поиск всех заказов клиента
     * @param customerId Id клиента
     * @return Список заказов клиента
     */
    Set<Orders> getAllCustomerOrders(long customerId);

    /**
     * Обновить заказ
       @param order Заказ, который нужно обновить
     */
    void updateOrder(Orders order);

    void delete(Customer customer);
}

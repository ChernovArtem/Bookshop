package ru.inno.shop.service.order.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inno.shop.model.cart.Cart;
import ru.inno.shop.model.cart.CartDetails;
import ru.inno.shop.model.cart.CartStatus;
import ru.inno.shop.model.customer.Customer;
import ru.inno.shop.model.order.Orders;
import ru.inno.shop.repository.order.OrderDao;
import ru.inno.shop.service.cart.CartService;
import ru.inno.shop.service.order.OrderService;
import ru.inno.shop.service.stock.StockService;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Артём Матюнин
 * Реализация сервиса заказа
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final CartService cartService;
    private final StockService stockService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, CartService cartService, StockService stockService, OrderMapper orderMapper) {
        this.orderDao = orderDao;
        this.cartService = cartService;
        this.stockService = stockService;
        this.orderMapper = orderMapper;
    }

    /**
     * Поиск всех заказов пользователя
     *
     * @param customerId Id клиента
     * @return Несортированный список заказов клиента.
     * @throws IllegalArgumentException если customerId <= 0
     */
    @Override
    @Transactional
    public Set<Orders> getAllCustomerOrders(long customerId) {
        if (customerId <= 0) {
            log.warn("Некорректный входящий параметр customerId {}", customerId);
            throw new IllegalArgumentException("Не удалось сохранить объект в БД");
        }
        Set<Orders> ordersSet = new HashSet<>();
        Optional<Set<Orders>> optionalOrders = orderDao.getOrdersByCustomerId(customerId);
        if (optionalOrders.isPresent()) {
            ordersSet.addAll(optionalOrders.get());
        }
        log.info("Получен список заказов клиента {}", customerId);
        return ordersSet;
    }

    /**
     * Просмотр подробеостей заказа клиента
     *
     * @param orderId Номер заказа
     * @return Заказ
     * @throws IllegalArgumentException если orderId <= 0
     */
    @Override
    @Transactional
    public Orders getOrderById(long orderId) {
        if (orderId <= 0) {
            log.warn("Некорректный входящий параметр orderId {}", orderId);
            throw new IllegalArgumentException("Не удалось сохранить объект в БД");
        }
        Orders order = new Orders();
        Optional<Orders> findOrder = orderDao.findById(orderId);
        if (findOrder.isPresent()) {
            order = findOrder.get();
            order.getOrderDetailsSet().addAll(order.getOrderDetailsSet());
        }
        log.info("Получен заказ {}", order.getOrderNumber());
        return order;
    }

    /**
     * Обновление заказа
     *
     * @param order Заказ с Id
     * @throws IllegalArgumentException если order==null
     */
    @Transactional
    @Override
    public void updateOrder(Orders order) {
        if (order == null) {
            log.warn("Некорректный входящий параметр order: NULL");
            throw new IllegalArgumentException("Не удалось сохранить объект в БД");
        }
        orderDao.save(order);
    }

    /**
     * Создание заказа. Получет корзину на вход, преобразует ее в заказ, а список товаров в корзине
     * в список товаров в заказе и сохраняет в БД.
     * Для преобразования объекта Cart в объект Orders использует -метод-маппер prepareOrder.
     * Для преобразования CartDetails в OrderDetails спользует метод-маппер cartDetailToOrderDetail.
     * Отправляет товары в резерв на складе, меняет статус корзины.
     *
     * @param cart Объект корзины. Будет преобразован в заказ.
     * @return сохраненный заказ
     * @throws IllegalArgumentException если cart == null
     */
    @Transactional
    @Override
    public Orders createOrder(Cart cart) {
        if (cart == null) {
            log.warn("Некорректный входящий параметр cart = NULL");
            throw new IllegalArgumentException("Не удалось сохранить объект в БД");
        }
        Orders or = orderDao.save(orderMapper.prepareOrder(cart));
        for (CartDetails c : cart.getCartDetailsSet()) {
            stockService.placeGoodsInReserve(c.getBook().getIsbn(), c.getCartDetailQuantity());
            or.getOrderDetailsSet().add(orderMapper.cartDetailToOrderDetail(or, c));
            log.info("В заказ {} добавлен товар {} в количестве {}",
                    or.getOrderNumber(), c.getCartDetailBook(), c.getCartDetailQuantity());
        }
        log.info("Создан заказ {} от клиента {}", or.getOrderNumber(), or.getCustomerId().getId());
        cartService.updateCart(cart, CartStatus.ORDERED);
        return or;
    }

    @Override
    public void delete(Customer customer) {
        Optional<Set<Orders>> optionalOrders = orderDao.getOrdersByCustomerId(customer.getId());
        if (!optionalOrders.isPresent()) {
            return;
        }

        orderDao.deleteAll(optionalOrders.get());
    }
}

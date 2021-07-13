package ru.inno.shop.service.cart;

import ru.inno.shop.model.cart.Cart;
import ru.inno.shop.model.cart.CartStatus;
import ru.inno.shop.model.customer.Customer;

import javax.transaction.Transactional;

/**
 * @author Артём Матюнин
 * Сервис работы с корзиной
 */
public interface CartService {

    /**
     * Создание или поиск активной корзины текущего пользователя (без предварительной загрузки списка товаров)
     *
     * @param customer имя текущего пользователя
     * @return возвращает корзину (новую или найденную)
     */
    @Transactional
    Cart createOrGetCartByUserName(Customer customer);

    /**
     * Получение корзины со списком покупок
     *
     * @param cart Объект корзины
     * @return текущая корзина вместе со списком товаров
     */
    @Transactional
    Cart getCartWithCartDetails(Cart cart);


    /**
     * Обновляет статус корзины
     * @param cart обновляемая корзина
     * @param cartStatus требуемый статус
     */
    @Transactional
    void updateCart(Cart cart, CartStatus cartStatus);

    void delete(Customer customer);
}

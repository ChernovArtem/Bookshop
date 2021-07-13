package ru.inno.shop.service.cart.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inno.shop.model.cart.Cart;
import ru.inno.shop.model.cart.CartStatus;
import ru.inno.shop.model.customer.Customer;
import ru.inno.shop.repository.cart.CartDao;
import ru.inno.shop.service.cart.CartDetailsService;
import ru.inno.shop.service.cart.CartService;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

/**
 * @author Артём Матюнин
 * Ревлизация сервиса для корзины
 */

@Slf4j
@Service
public class CartServiceImpl implements CartService {

    private final CartDao cartDao;

    private final CartDetailsService cartDetailsService;

    @Autowired
    public CartServiceImpl(CartDao cartDao, CartDetailsService cartDetailsService) {
        this.cartDao = cartDao;
        this.cartDetailsService = cartDetailsService;
    }

    /**
     * Если активная корзина есть в таблице cart, то вовзвращает ее.
     * Если актвиной корзины нет, то создает новую со статусом active и возвращает ее
     * Используется для получения объекта корзина без списка товаров
     *
     * @param customer поиск корзины по клиенту
     * @return возвращает корзину текущего пользователя.
     * @throws IllegalArgumentException Если входящие customer = null
     */
    @Override
    @Transactional
    public Cart createOrGetCartByUserName(Customer customer) {
        if (customer == null) {
            log.warn("Передан некорретный параметр customer = NULL");
            throw new IllegalArgumentException("Не удалось сохранить запись в БД");
        }
        Optional<Cart> findOne = cartDao.findByCartCustomerAndCartStatus(customer.getId(), CartStatus.ACTIVE);
        if (!findOne.isPresent()) {
            Cart cart = new Cart();
            cart.setCartDate(Date.from(Instant.now()));
            cart.setCartStatus(CartStatus.ACTIVE); //при создании корзины присваваем ей активный статус
            cart.setCartCustomer(customer);
            return cartDao.save(cart);
        }
        log.info("Запрошена корзина id = {}", findOne.get().getCartId());
        return findOne.get();
    }

    /**
     * Обновляет данные и/или статус корзины.
     *
     * @param cart       обновляемая корзина
     * @param cartStatus требуемый статус
     * @throws IllegalArgumentException Если входящие параметры null
     */
    @Override
    @Transactional
    public void updateCart(Cart cart, CartStatus cartStatus) {
        if (cart == null || cartStatus == null) {
            log.warn("cart или cartStatus NULL");
            throw new IllegalArgumentException("Не удалось сохранить запись в БД");
        }
        cart.setCartStatus(cartStatus);
        log.info("Обновлена корзина id {}. Присвоен статус {}", cartDao.save(cart).getCartId(), cartStatus);
    }

    /**
     * Используется для получения объекта корзины вместо со списком покупок
     * Получает из БД корзину текущего пользователя, заполняет список товаров
     *
     * @param cart Объект корзины из контроллера
     * @return заполненный объект Cart вместе с наполнением
     * @throws IllegalArgumentException если cart = null
     */
    @Override
    @Transactional
    public Cart getCartWithCartDetails(Cart cart) {
        if (cart == null) {
            log.warn("Передан некорретный параметр cart = NULL");
            throw new IllegalArgumentException("Не удалось сохранить запись в БД");
        }

        Cart findCart = new Cart();
        Optional<Cart> optionalCart = cartDao.findById(cart.getCartId());
        if (optionalCart.isPresent()) {
            findCart = optionalCart.get();
            findCart.setCartDetailsSet(findCart.getCartDetailsSet());
        }
        log.info("Запрошена корзина id {}", findCart.getCartId());
        return findCart;
    }

    @Override
    public void delete(Customer customer) {
        Optional<Set<Cart>> optional = cartDao.findAllByCartCustomer(customer.getId());
        if (!optional.isPresent()) {
            return;
        }
        Set<Cart> carts = optional.get();

        carts.forEach(cart -> cartDetailsService.deleteAllByCart(cart));
        cartDao.deleteAll(carts);
    }
}
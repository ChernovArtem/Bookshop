package ru.inno.shop.service.cart.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inno.shop.model.cart.Cart;
import ru.inno.shop.model.cart.CartDetails;
import ru.inno.shop.repository.cart.CartDetailDao;
import ru.inno.shop.service.cart.CartDetailsService;
import ru.inno.shop.service.stock.StockService;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

/**
 * @author Артём Матюнин
 * Реализация сервиса списка покупок в корзинре
 */
@Slf4j
@Service
public class CartDetailsServiceImpl implements CartDetailsService {

    private final StockService stockService;
    private final CartDetailDao cartDetailDao;

    @Autowired
    public CartDetailsServiceImpl(StockService stockService, CartDetailDao cartDetailDao) {
        this.stockService = stockService;
        this.cartDetailDao = cartDetailDao;
    }

    /**
     * Проверяет доступное количество на складе. Если товара достаточно, то возвращает запрошенное количество
     * Если нет, то возвращает 0.
     * Если такой объект уже есть в корзине, то обновляет его количество
     * Если такого объекта нет, то добавляет его.
     *
     * @param cartDetail объект, полностью подготовленный для записи в Базу
     * @return Запрошенное количество или количество на складе, если обновление не прошло.
     * @throws IllegalArgumentException если cartDetail = NULL
     */
    @Override
    @Transactional
    public int saveOrUpdateCartDetail(CartDetails cartDetail) {
        if (cartDetail == null) {
            log.warn("Входящие параметры некорректны = NULL");
            throw new IllegalArgumentException("Не удалось выполнить запись в базу");
        }
        int stockAvailable = stockService.getStockByISBN(cartDetail.getCartDetailBook()).getAvailable();
        if (stockAvailable >= cartDetail.getCartDetailQuantity()) {
            log.info(
                    "Изменено количество {} товаров {} в корзине {}",
                    cartDetail.getCartDetailQuantity(),
                    cartDetail.getCartDetailBook(),
                    cartDetail.getCartId()
            );
            return cartDetailDao.save(cartDetail).getCartDetailQuantity();

        }
        log.info(
                "Не удалось обновить количество {} товаров {}. На скаладе доступно {}",
                cartDetail.getCartDetailBook(),
                cartDetail.getCartDetailQuantity(),
                stockAvailable
        );
        return stockAvailable;
    }

    /**
     * Удаляет строку с товаром из корзины
     *
     * @param cartDetails удаляемой строки
     * @throws IllegalArgumentException если cartDetails = NULL
     */
    @Override
    public void deleteCartDetail(CartDetails cartDetails) {
        if (cartDetails == null) {
            log.warn("Входящие параметры cartDetails = NULL");
            throw new IllegalArgumentException("Не удалось выполнить запись в базу");
        }
        cartDetailDao.delete(cartDetails);
        log.info("Удалена запись {} {}", cartDetails.getCart(), cartDetails.getCartId());
    }

    @Override
    public void deleteAllByCart(Cart cart) {
        Optional<Set<CartDetails>> cartDetailsSet = cartDetailDao.findAllByCartId(cart.getCartId());
        if (!cartDetailsSet.isPresent()) {
            return;
        }

        cartDetailDao.deleteAll(cartDetailsSet.get());
    }
}

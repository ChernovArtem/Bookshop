package ru.inno.shop.service.cart;

import ru.inno.shop.model.cart.Cart;
import ru.inno.shop.model.cart.CartDetails;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Артём Матюнин
 * Сервис списка покупок в корзине
 */
public interface CartDetailsService {

    /**
     * Сохранение товара в корзине или обновление его количества, если он там есть
     *
     * @param cartDetail объект для сохранения в БД
     * @return Запрошенное количество в случае успеха, или количество на складе, если обновление не прошло.
     */
    @Transactional
    int saveOrUpdateCartDetail(CartDetails cartDetail);

    /**
     * @param cartDetail Удаляемый из корзины товар
     */
    @Transactional
    void deleteCartDetail(CartDetails cartDetail);

    void deleteAllByCart(Cart cart);
}

package ru.inno.shop.repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inno.shop.model.cart.Cart;
import ru.inno.shop.model.cart.CartStatus;
import java.util.Optional;
import java.util.Set;

/**
 * @author Артём Матюнин
 * Репозиторий корзины
 *
 */
public interface CartDao extends JpaRepository<Cart,Long> {

    /**
     * Поиск корзины по клиенту и статусу
     * @param cartCustomer Владелец корзины
     * @param cartStatus Текущий статус
     * @return Optional с найденной корзиной
     */
    Optional<Cart> findByCartCustomerAndCartStatus(Long cartCustomer, CartStatus cartStatus);

    Optional<Set<Cart>> findAllByCartCustomer(Long cartCustomer);
}

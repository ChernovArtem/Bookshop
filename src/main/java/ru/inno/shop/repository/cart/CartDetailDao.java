package ru.inno.shop.repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inno.shop.model.cart.CartDetails;

import java.util.Optional;
import java.util.Set;

/**
 * @author Артём Матюнин
 * Репоизторий для работы со списком покупок корзины
 */

public interface CartDetailDao extends JpaRepository<CartDetails, Long> {

    Optional<Set<CartDetails>> findAllByCartId(Long cartId);
}

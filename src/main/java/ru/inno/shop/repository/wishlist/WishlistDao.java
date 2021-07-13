package ru.inno.shop.repository.wishlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.inno.shop.model.wishlist.Wishlist;
import java.util.List;
import java.util.Optional;

/**
 * Репозитория для работы с избранным
 * @author Chernov Artem
 */
public interface WishlistDao extends JpaRepository<Wishlist,Long> {

    /**
     * Поиск избранного по клиенту
     * @param customerId клиент
     */
    Optional<Wishlist> findByCustomer(Long customerId);

    @Query(value = "select count(b.book) as count_wishlist, cart.count as count_cart from wishlist w" +
            " left join wishlist_book b on b.id = w.id" +
            " left join (select sum(d.cart_detail_quantity) as count, c.cart_customer from cart c" +
            "  join cart_details d on d.cart_id = c.cart_id where c.cart_status = 0 group by c.cart_customer) cart" +
            "  on cart.cart_customer = w.customer" +
            " where w.customer = ?1" +
            " group by cart.count", nativeQuery = true)
    List<Object[]> getCountByCustomer(Long customerId);
}

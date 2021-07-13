package ru.inno.shop.repository.wishlist;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inno.shop.model.wishlist.WishlistBook;

/**
 * Репозитория для работы с избранным
 * @author Chernov Artem
 */
public interface WishlistBookDao extends JpaRepository<WishlistBook,Long> {

    WishlistBook findByIdAndBookIsbn(Long id, String isbn);
}

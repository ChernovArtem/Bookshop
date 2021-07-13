package ru.inno.shop.service.wishlist;

import ru.inno.shop.model.customer.Customer;
import ru.inno.shop.model.wishlist.Wishlist;
import ru.inno.shop.model.wishlist.WishlistBook;
import java.util.Set;

/**
 * Сервис для работы с избранным (фасад)
 * @author Chernov Artem
 */
public interface WishlistService {

    Wishlist addWishlist(Wishlist wishlist);

    Set<WishlistBook> getAllBook();

    boolean addBookWishlist(WishlistBook wishlistBook);

    void deleteBook(Long id, String isbn);

    void delete(Customer customer);

    Object[] getCount();
}

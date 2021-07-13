package ru.inno.shop.service.wishlist.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inno.shop.model.book.Book;
import ru.inno.shop.model.customer.Customer;
import ru.inno.shop.model.user.User;
import ru.inno.shop.model.wishlist.Wishlist;
import ru.inno.shop.model.wishlist.WishlistBook;
import ru.inno.shop.repository.wishlist.WishlistBookDao;
import ru.inno.shop.repository.wishlist.WishlistDao;
import ru.inno.shop.service.book.BookService;
import ru.inno.shop.service.user.UserService;
import ru.inno.shop.service.wishlist.WishlistService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Сервис для работы с избранным (реализация)
 * @author Chernov Artem
 */
@Slf4j
@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistDao wishlistDao;

    @Autowired
    private WishlistBookDao wishlistBookDao;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Override
    public Wishlist addWishlist(Wishlist wishlist) {
        return wishlistDao.save(wishlist);
    }

    @Override
    public Set<WishlistBook> getAllBook() {
        Wishlist wishlist = getWishlist();
        if (wishlist == null) {
            return null;
        }

        return wishlist.getWishlistBooks();
    }

    @Override
    public boolean addBookWishlist(WishlistBook wishlistBook) {
        Book book = bookService.getBookByIsbn(wishlistBook.getBookIsbn());
        if (book == null) {
            return false;
        }

        Wishlist wishlist = getWishlist();
        if (wishlist == null) {
            return false;
        }

        wishlistBook.setId(wishlist.getId());
        wishlistBook.setBook(book);
        wishlistBookDao.save(wishlistBook);

        return true;
    }

    private User getCurrentAuthUser() {
        return userService.getCurrentAuthUser();
    }

    private Wishlist getWishlist() {
        User currentAuthUser = getCurrentAuthUser();
        if (currentAuthUser == null) {
            return null;
        }

        Optional<Wishlist> wishlist = wishlistDao.findByCustomer(currentAuthUser.getId());
        if (!wishlist.isPresent()) {
            return null;
        }

        return wishlist.get();
    }

    @Override
    public void deleteBook(Long id, String isbn) {
        WishlistBook wishlistBook = wishlistBookDao.findByIdAndBookIsbn(id, isbn);
        if (wishlistBook == null) {
            return;
        }
        wishlistBookDao.delete(wishlistBook);
    }

    @Override
    public void delete(Customer customer) {
        Optional<Wishlist> wishlist = wishlistDao.findByCustomer(customer.getId());
        if (!wishlist.isPresent()) {
            return;
        }

        wishlistBookDao.deleteAll(wishlist.get().getWishlistBooks());

        wishlistDao.delete(wishlist.get());
    }

    @Override
    public Object[] getCount() {
        User currentAuthUser = getCurrentAuthUser();
        if (currentAuthUser == null
                || !currentAuthUser.getRoles().stream().anyMatch((role -> role.getName().equals("ROLE_CONSUMER")))) {
            return null;
        }

        List<Object[]> list = wishlistDao.getCountByCustomer(currentAuthUser.getId());
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
package ru.inno.shop.service.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.inno.shop.model.book.Book;
import java.util.List;

/**
 * Сервис по работе с книгами
 * @author Chernov Artem
 */
public interface BookService {

    /**
     * Получение страниц с сущностями книг
     * @param pageable - класс с настройками страницы
     * @return страницу с сущностями книг
     */
    Page<Book> getPageBooks(Pageable pageable);

    /**
     * Получение страниц с сущностями книг по жанру
     * @param genre - жанр книг
     * @param pageable - класс с настройками страницы
     * @return страницу с сущностями книг по жанру
     */
    Page<Book> getPageBooksByGenre(String genre, Pageable pageable);

    /**
     *
     * @return
     */
    List<Book> getAll();

    /**
     *
     * @param genre
     * @return
     */
    List<Book> getAllByGenre(String genre);

    /**
     *
     * @param isbn
     * @return
     */
    Book getBookByIsbn(String isbn);

    /**
     *
     * @param book
     */
    void save(Book book);

    /**
     *
     * @param isbn
     */
    void delete(String isbn);

    /**
     * Поиск сущности книг
     * @param keyword - значение для поиска
     * @return список найденных книг
     */
    List<Book> search(String keyword);
}

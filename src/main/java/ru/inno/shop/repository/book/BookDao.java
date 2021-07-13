package ru.inno.shop.repository.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.inno.shop.model.book.Book;
import java.util.List;

public interface BookDao extends JpaRepository<Book, String> {

    @Query(value = "SELECT b FROM Book b join Genre g on b.isbn = g.isbn where g.genre like :genre")
    List<Book> findBookByGenre(@Param("genre") String genre);

    @Query(value = "select b from Book b join Genre g on b.isbn = g.isbn where g.genre like :genre")
    Page<Book> findBooksByGenre(@Param("genre") String genre, Pageable pageable);

    @Query(value = "select b from Book b"
            + " join Genre g on b.isbn = g.isbn"
            + " where b.isbn like concat('%', :keyword, '%')"
            + " OR lower(b.title) like lower(concat('%', :keyword, '%'))"
            + " OR lower(b.type) like lower(concat('%', :keyword, '%'))"
            + " OR lower(g.genre) like lower(concat('%', :keyword, '%'))")
    List<Book> search(@Param("keyword") String keyword);
}
package ru.inno.shop.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.inno.shop.model.book.Genre;
import java.util.List;

@Repository
public interface GenreDao extends JpaRepository<Genre, String> {

    @Query(value = "select genre, count(isbn) as count, (select count(isbn) as allCount from genres)" +
            " from genres group by genre", nativeQuery = true)
    List<Object[]> findGenreAndCount();
}

package ru.inno.shop.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inno.shop.model.book.Authors;

public interface AuthorsDao extends JpaRepository<Authors, Integer> {

    Authors findByAuthorName(String authorName);
}
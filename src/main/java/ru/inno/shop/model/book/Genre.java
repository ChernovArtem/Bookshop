package ru.inno.shop.model.book;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Сущность жанров книг
 */
@Setter
@Getter
@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "genre")
    private String genre;
}

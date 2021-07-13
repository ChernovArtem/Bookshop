package ru.inno.shop.model.wishlist;

import lombok.Getter;
import lombok.Setter;
import ru.inno.shop.model.book.Book;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Сущность избранного, как отдельные книги
 * @author Chernov Artem
 */
@Entity
@Setter
@Getter
@Table(name = "wishlist_book")
@IdClass(WishlistBookPK.class)
public class WishlistBook {

    @Id
    @Column(name = "id")
    private Long id;

    @Id
    @Column(name = "book")
    private String bookIsbn;

    @ManyToOne
    @JoinColumn (name="book", insertable = false, updatable = false)
    private Book book;
}

package ru.inno.shop.model.wishlist;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Класс составного первичного ключа избранного
 */
@Setter
@Getter
@EqualsAndHashCode
public class WishlistBookPK {

    @Id
    @Column(name = "id")
    private Long id;

    @Id
    @Column(name = "book")
    private String bookIsbn;
}

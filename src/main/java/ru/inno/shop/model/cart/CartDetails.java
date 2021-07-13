package ru.inno.shop.model.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Артём Матюнин
 * Модель элемента корзины
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cart_details", schema = "public", catalog = "onlineShop")
@IdClass(CartDetailsPK.class)
public class CartDetails {

    @Column(name = "cart_id")
    @Id
    private Long cartId;

    @Column(name = "cart_detail_book")
    @Id
    private String cartDetailBook;

    /** Связь с козиной */
    @ManyToOne
    @JoinColumn (name="cart_id", insertable = false, updatable = false)
    private Cart cart;

    /** Выбранная книга */
    @ManyToOne
    @JoinColumn (name="cart_detail_book", insertable = false, updatable = false)
    private ru.inno.shop.model.book.Book Book;

    /** Количество выбранной книги */
    @Basic
    @Column(name = "cart_detail_quantity")
    private Integer cartDetailQuantity;

}

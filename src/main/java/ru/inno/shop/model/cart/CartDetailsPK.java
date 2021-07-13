package ru.inno.shop.model.cart;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author Артём
 * Класс составного первичного ключа деталей корзины
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CartDetailsPK {

    @Id
    @Column(name = "cart_id")
    private Long cartId;

    @Id
    @Column(name = "cart_detail_book")
    private String cartDetailBook;
}

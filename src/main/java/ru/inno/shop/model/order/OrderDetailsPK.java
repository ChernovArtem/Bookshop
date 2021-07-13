package ru.inno.shop.model.order;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author Артём Матюнин
 * Первичный ключ для товара в заказе
 */
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsPK {

    @Id
    @Column(name = "order_number")
    private Long orderNumber;

    @Id
    @Column(name = "product_id")
    private String productId;
}

package ru.inno.shop.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.inno.shop.model.book.Book;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author Артём Матюнин
 * Сущность товар в заказе
 * Имеет составной ключ, реализованный в классе OrderDetailsPK
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrderDetailsPK.class)
@Table(name = "order_details", schema = "public", catalog = "onlineShop")
public class OrderDetails {

    @Id
    @Column(name = "order_number")
    private Long orderNumber;

    @Id
    @Column(name = "product_id")
    private String productId;

    /** Книга */
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Book book;

    /** Заказ */
    @ManyToOne
    @JoinColumn(name = "order_number", insertable = false, updatable = false)
    private Orders order;

    /** Количество */
    @Basic
    @Column(name = "quantity_ordered")
    private Integer quantityOrdered;

    /** Стоимость */
    @Basic
    @Column(name = "price")
    private BigDecimal price;
}
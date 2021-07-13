package ru.inno.shop.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.inno.shop.model.customer.Customer;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Артём Матюнин
 * Сущность заказ
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders", schema = "public", catalog = "onlineShop")
public class Orders {

    @Id
    @Setter
    @Column(name = "order_number", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNumber;

    @Setter
    @Temporal(TemporalType.DATE)
    @Column(name = "order_date")
    private Date orderDate;

    @Setter
    @Temporal(TemporalType.DATE)
    @Column(name = "required_date")
    private Date requiredDate;

    @Setter
    @Temporal(TemporalType.DATE)
    @Column(name = "shipped_date")
    private Date shippedDate;

    @Setter
    @Column(name = "price")
    private Float price;

    @Setter
    @Column(name = "delivery")
    private Float delivery;

    @Setter
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private OrderStatus status;

    @Basic
    @Setter
    @Column(name = "comments")
    private String comments;

    @Setter
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customerId;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private final Set<OrderDetails> orderDetailsSet = new TreeSet<>(Comparator.comparing(OrderDetails::getProductId));
   }

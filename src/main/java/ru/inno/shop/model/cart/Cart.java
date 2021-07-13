package ru.inno.shop.model.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SortComparator;
import ru.inno.shop.model.customer.Customer;
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
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Артём Матюнин
 * Модель корзины
 * Принадлежит опреденному клиенту, имеет дату создания, статус и состав(список товаров)
 * Может быть в одном из трех статусов:
 * - active(действующая)
 * - removed(забыта и удалена)
 * - ordered(перешла в заказ)
 */

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
public class Cart {

    @Id
    @Setter
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @Setter
    @Temporal(TemporalType.DATE)
    @Column(name = "cart_date")
    private Date cartDate;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name= "cart_customer")
    private Customer cartCustomer;

    @Setter
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "cart_status")
    private CartStatus cartStatus;

    @SortComparator(CartDetailsComparator.class)
    @OneToMany(mappedBy = "cartId", fetch = FetchType.LAZY)
    private final Set<CartDetails> cartDetailsSet = new TreeSet<>();

    public void setCartDetailsSet(Collection<CartDetails> cartDetailsCollection) {
        this.cartDetailsSet.addAll(cartDetailsCollection);
    }
}

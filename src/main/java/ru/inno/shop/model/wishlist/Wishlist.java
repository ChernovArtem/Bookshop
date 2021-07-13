package ru.inno.shop.model.wishlist;

import lombok.Getter;
import lombok.Setter;
import ru.inno.shop.model.customer.Customer;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

/**
 * Сущность избранного
 * @author Chernov Artem
 */
@Entity
@Setter
@Getter
@Table(name = "wishlist")
public class Wishlist {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "customer", referencedColumnName = "user_id")
    private Customer customer;

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
    private Set<WishlistBook> wishlistBooks;
}
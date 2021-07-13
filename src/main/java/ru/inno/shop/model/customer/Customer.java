package ru.inno.shop.model.customer;

import lombok.Getter;
import lombok.Setter;
import ru.inno.shop.model.user.User;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Сущность зарегистрированного пользователя (клиент)
 * @author Chernov Artem
 */
@Entity
@Setter
@Getter
@Table(name = "customers")
public class Customer {

    /** Идентификатор пользователя */
    @Id
    @Column(name = "user_id")
    private Long id;

    /** Связь на таблицу пользователей */
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    /** Имя зарегистрированного пользователя */
    @Setter
    @Column(name = "firstname")
    private String firstname;

    /** Фамилия зарегистрированного пользователя */
    @Column(name = "lastname")
    private String lastname;

    /** Почта для страницы аккаунта */
    @Transient
    private String email;

    /** Номер телефона зарегистрированного пользователя */
    @Column(name = "phone")
    private String phone;

    /** Адрес зарегистрированного пользователя */
    @Column(name = "address")
    private String address;

    /** Адрес зарегистрированного пользователя */
    @Column(name = "street")
    private String street;

    /** Город зарегистрированного пользователя */
    @Column(name = "city")
    private String city;

    /** Область/штат зарегистрированного пользователя */
    @Column(name = "state")
    private String state;

    /** Почтовый код зарегистрированного пользователя */
    @Column(name = "postal_code")
    private String postal;

    /** Страна зарегистрированного пользователя */
    @Column(name = "country")
    private String country;
}

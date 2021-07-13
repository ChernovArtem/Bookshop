package ru.inno.shop.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Set;

/**
 * Class User
 * для регистрации пользователей в БД
 */
@Entity
@Setter
@Getter
@Table(name = "users")
public class User {

    /** Идентификатор */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Логин пользователя */
    @Column(name = "username")
    private String username;

    /** Эл. почта пользователя */
    @Column(name = "email")
    private String email;

    /** Пароль */
    @JsonIgnore
    @Column(name = "password")
    private String password;

    /** Поле для подтверждения пароля при регистрации */
    @JsonIgnore
    @Transient
    private String confirmPassword;

    /** Роли пользователя */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}

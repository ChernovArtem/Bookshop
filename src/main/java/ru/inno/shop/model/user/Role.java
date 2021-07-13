package ru.inno.shop.model.user;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Сущность с названиями ролей
 */
@Entity
@Setter
@Getter
@Table(name = "roles")
public class Role {

    /** Идентификатор */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Название роли */
    @Column(name = "name")
    private String name;
}
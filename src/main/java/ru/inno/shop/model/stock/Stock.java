package ru.inno.shop.model.stock;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.inno.shop.model.book.Book;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * Сущность склада
 * @author Chernov Artem
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stock")
public class Stock {

    /** Идентификатор книги */
    @Id
    @Column(name = "isbn")
    private String isbn;

    /** Связь на таблицу пользователей */
    @JsonIgnore
    @CsvIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "isbn")
    private Book book;

    /** Название книги */
    @Transient
    private String nameBook;

    /** Общее кол-во книг на складе */
    @NotNull
    @Column(name = "total")
    private Integer total;

    /** Кол-во проданных книг */
    @NotNull
    @Column(name = "sold")
    private Integer sold;

    /** Кол-во зарезервированных книг */
    @NotNull
    @Column(name = "reserve")
    private Integer reserve;

    /** Кол-во книг в продаже (total - reserve) */
    @NotNull
    @Column(name = "available")
    private Integer available;
}

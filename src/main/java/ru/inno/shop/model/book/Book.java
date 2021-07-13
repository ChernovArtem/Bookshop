package ru.inno.shop.model.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.inno.shop.model.stock.Stock;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Set;

/**
 * Сущность книг
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "books")
public class Book {

    /** Идентификат книги */
    @Id
    @Column(name = "isbn")
    private String isbn;

    /** Название книги */
    @Basic
    @Column(name = "book_title")
    private String title;

    /** Тип книги (печатная, электронная) */
    @Basic
    @Column(name = "book_type")
    private String type;

    /** Год публикации */
    @Basic
    @Column(name = "publishing_year")
    private int publishingYear;

    /** Количество страниц */
    @Basic
    @Column(name = "pages")
    private Integer pages;

    /** Стоимость книги */
    @Basic
    @Column(name = "price")
    private int price;

    /** Для отображения авторов и их редактирования на странице */
    @Transient
    private String authorsStr;

    /** Авторы книги */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_by_authors",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Authors> authors;

    /** Жанр книги */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "isbn", insertable = false, updatable = false)
    private Genre genre;

    /** Информация о книге на складе (всего количество, проданных, в резерве) */
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Stock stock;
}

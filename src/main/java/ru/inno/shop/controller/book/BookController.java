package ru.inno.shop.controller.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inno.shop.model.book.Book;
import ru.inno.shop.service.book.BookService;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getAllBooks(){
        return bookService.getAll();
    }

    @GetMapping(params = {"genre", "page", "size", "sortType"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Book>> getStocks(@Param("genre") String genre, @Param("page") int page,
                                                @Param("size") int size, @Param("sortType") int sortType) {

        Sort sort;
        switch (sortType) {
            case 1:
                sort = Sort.by("price");
                break;
            case 2:
                sort = Sort.by("price").descending();
                break;
            case 3:
                sort = Sort.by("title");
                break;
            default:
                sort = Sort.unsorted();
        }
        Pageable requestedPage = PageRequest.of(page, size, sort);

        Page<Book> stocks;
        if (!genre.isEmpty()) {
            stocks = bookService.getPageBooksByGenre(genre, requestedPage);
        } else {
            stocks = bookService.getPageBooks(requestedPage);
        }

        return Objects.nonNull(stocks) ? ResponseEntity.ok(stocks) : ResponseEntity.noContent().build();
    }

    @GetMapping(params = "isbn", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book getBookByIsbn(@Param("isbn") String isbn){
        return bookService.getBookByIsbn(isbn);
    }

    @GetMapping(value = "/genres/{genre}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getAllBooksByGenre(@PathVariable String genre){
        return bookService.getAllByGenre(genre);
    }

}

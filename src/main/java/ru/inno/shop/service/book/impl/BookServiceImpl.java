package ru.inno.shop.service.book.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.inno.shop.model.book.Authors;
import ru.inno.shop.model.book.Book;
import ru.inno.shop.model.book.Genre;
import ru.inno.shop.model.stock.Stock;
import ru.inno.shop.repository.book.AuthorsDao;
import ru.inno.shop.repository.book.BookDao;
import ru.inno.shop.repository.book.GenreDao;
import ru.inno.shop.service.book.BookService;
import ru.inno.shop.service.stock.StockService;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private AuthorsDao authorsDao;

    @Autowired
    private StockService stockService;

    @Override
    public Page<Book> getPageBooks(Pageable pageable) {
        Page<Book> booksPage = bookDao.findAll(pageable);
        addAuthorsStrForPage(booksPage);
        return booksPage;
    }

    @Override
    public Page<Book> getPageBooksByGenre(String genre, Pageable pageable) {
        Page<Book> booksPage = bookDao.findBooksByGenre(genre, pageable);
        addAuthorsStrForPage(booksPage);
        return booksPage;
    }

    @Override
    public List<Book> getAll() {
        return bookDao.findAll();
    }

    @Override
    public List<Book> getAllByGenre(String genre) {
        return bookDao.findBookByGenre(genre);
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        Optional<Book> optional = bookDao.findById(isbn);
        if (!optional.isPresent()) {
            log.warn("Не удалось найти книгу по isbn = {}", isbn);
            return null;
        }
        Book book = optional.get();
        addAuthorsStr(book);
        return book;
    }

    @Override
    public void save(Book book) {

        String authorsStr = book.getAuthorsStr();
        Set<Authors> authorsSet = new HashSet<>();
        if (authorsStr.contains(",")) {
            String[] authors = authorsStr.split(", ");
            for (String author : authors) {
                Authors authorClass = authorsDao.findByAuthorName(author);
                if (authorClass == null) {
                    authorClass =createAuthor(author);
                }
                authorsSet.add(authorClass);
            }
        } else {
            Authors authorClass = authorsDao.findByAuthorName(authorsStr);
            if (authorClass == null) {
                authorClass =createAuthor(authorsStr);
            }
            authorsSet.add(authorClass);
        }
        book.setAuthors(authorsSet);

        book.getGenre().setIsbn(book.getIsbn());

        Optional<Book> oldBook = bookDao.findById(book.getIsbn());
        if (oldBook.isPresent()) {
            bookDao.save(book);
        } else {
            Genre genre = book.getGenre();
            book.setGenre(null);

            Stock stock = new Stock(null, book, book.getTitle(), 0, 0, 0, 0);
            stockService.addStock(stock);

            genreDao.save(genre);
        }
    }

    private Authors createAuthor(String authorStr) {
        Authors author = new Authors();
        author.setAuthorName(authorStr);
        author = authorsDao.save(author);
        return author;
    }

    @Override
    public void delete(String isbn) {
        Book book = getBookByIsbn(isbn);

        genreDao.delete(book.getGenre());
        book.setGenre(null);

        stockService.delete(book.getStock());
        book.setStock(null);

        bookDao.delete(book);
    }

    @Override
    public List<Book> search(String keyword) {
        return bookDao.search(keyword);
    }

    private void addAuthorsStrForPage(Page<Book> booksPage) {
        booksPage.getContent().forEach(book -> addAuthorsStr(book));
    }

    private void addAuthorsStr(Book book) {
        StringBuilder nameAuthors = new StringBuilder();

        for (Authors author : book.getAuthors()) {
            if (nameAuthors.length() > 0) {
                nameAuthors.append(", ");
            }
            nameAuthors.append(author.getAuthorName());
        }
        book.setAuthorsStr(nameAuthors.toString());
    }
}

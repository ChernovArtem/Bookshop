package ru.inno.shop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.inno.shop.model.book.Book;
import ru.inno.shop.service.book.BookService;
import ru.inno.shop.utils.image.ImageUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class AdminBookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ImageUtils imageUtils;

    /**
     * Получение всех книг
     */
    @GetMapping("/adminBooks")
    public ModelAndView allBooks() {
        List<Book> books = bookService.getAll();
        ModelAndView mav = new ModelAndView("admin/book/adminBooks");
        mav.addObject("booksList", books);
        return mav;
    }

    /**
     * Получение страницы для добавления новой книги
     */
    @GetMapping("/adminBookNew")
    public String newBookForm(Map<String, Object> model) {
        Book book = new Book();
        model.put("newBook", book);
        return "admin/book/new_book";
    }

    /**
     * Сохранение книги
     */
    @PostMapping("/adminBookSave")
    public String saveBook(@ModelAttribute("book") Book book, @RequestParam("image") MultipartFile imagefile,
                           HttpServletRequest httpServletRequest) throws IOException {
        imageUtils.save(httpServletRequest, imagefile, book.getIsbn());
        bookService.save(book);
        return "redirect:/adminBooks";
    }

    /**
     * Получение страницы для изменения книги
     */
    @GetMapping("/adminBookEdit")
    public ModelAndView editBookForm(@RequestParam String isbn) {
        ModelAndView mav = new ModelAndView("admin/book/adminBooksEdit");
        Book book = bookService.getBookByIsbn(isbn);
        mav.addObject("editBook", book);
        return mav;
    }

    /**
     * Удаление книги по его id
     */
    @GetMapping("/adminBookDelete")
    public String deleteBookForm(@RequestParam String isbn) {
        bookService.delete(isbn);
        return "redirect:/adminBooks";
    }

    /**
     * Поиск книг по ключевому слову
     * @param keyword - ключ по которому искать
     */
    @GetMapping("/adminBooksSearch")
    public ModelAndView search(@RequestParam String keyword) {
        List<Book> books_result = bookService.search(keyword);
        ModelAndView mav = new ModelAndView("admin/book/adminBooksSearch");
        mav.addObject("booksList", books_result);
        return mav;
    }
}

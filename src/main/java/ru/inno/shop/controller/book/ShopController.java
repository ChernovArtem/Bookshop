package ru.inno.shop.controller.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.inno.shop.model.book.Book;
import ru.inno.shop.service.book.BookService;
import java.util.List;

@Controller
public class ShopController {

    @Autowired
    private BookService bookService;

    @GetMapping("/shop")
    public String start() {
        return "forward:/resources/shop.html";
    }

    @GetMapping(value = "/shop", params = "genre")
    public String getGenreShop(@RequestParam("genre") String genre) {
        return "forward:/resources/shop.html";
    }

    /**
     * Поиск пользователей по ключевому слову
     * @param keyword - ключ по которому искать
     */
    @GetMapping("/shopSearch")
    public ModelAndView search(@RequestParam String keyword) {
        List<Book> books_result = bookService.search(keyword);
        ModelAndView mav = new ModelAndView("/shop/shopSearch");
        mav.addObject("booksList", books_result);
        return mav;
    }
}
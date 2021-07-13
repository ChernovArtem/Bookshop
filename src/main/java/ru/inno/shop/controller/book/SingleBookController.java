package ru.inno.shop.controller.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SingleBookController {

    @GetMapping(value = "/book/{isbn}")
    public String getSingleBookPage(@PathVariable String isbn){
        return "forward:/resources/single-product.html";
    }
}

package ru.inno.shop.controller.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.inno.shop.model.wishlist.WishlistBook;
import ru.inno.shop.service.wishlist.WishlistService;

import java.util.Objects;
import java.util.Set;

/**
 * Контроллер для работы с избранным
 */
@Controller
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @Secured(value = "ROLE_CONSUMER")
    @GetMapping("/wishlist")
    public String getWishlist(Model model) {

        Set<WishlistBook> wishlistBooks = wishlistService.getAllBook();
        model.addAttribute("wishlist", wishlistBooks);

        return "wishlist/wishlist";
    }

    @ResponseBody
    @PutMapping(value = "/wishlist", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WishlistBook> addBook(@RequestBody WishlistBook wishlistBook) {
        boolean result = wishlistService.addBookWishlist(wishlistBook);
        return result ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @Secured(value = "ROLE_CONSUMER")
    @GetMapping("/wishlistDelete")
    public String deleteBookForm(@RequestParam Long id, @RequestParam String isbn) {
        wishlistService.deleteBook(id, isbn);
        return "redirect:/wishlist";
    }

    @ResponseBody
    @PostMapping("/wishlistCount")
    public ResponseEntity<Object[]> getCount() {
        Object[] count = wishlistService.getCount();
        return Objects.nonNull(count) ? ResponseEntity.ok(count) : ResponseEntity.noContent().build();
    }
}
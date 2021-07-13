package ru.inno.shop.controller.cart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.inno.shop.model.cart.Cart;
import ru.inno.shop.model.cart.CartDetails;
import ru.inno.shop.model.customer.Customer;
import ru.inno.shop.model.user.User;
import ru.inno.shop.service.cart.CartDetailsService;
import ru.inno.shop.service.cart.CartService;
import ru.inno.shop.service.customer.CustomerService;
import ru.inno.shop.service.user.UserService;

/**
 * @author Артём Матюнин
 * Контроллер для работы с корзиной и списком покупок
 */
@Slf4j
@Controller
@SessionAttributes(value = "cart")
public class CartController {

    private final CartService cartService;
    private final UserService userService;
    private final CustomerService customerService;
    private final CartDetailsService cartDetailsService;

    @Autowired
    public CartController(CartService cartService, UserService userService, CustomerService customerService,
                          CartDetailsService cartDetailsService) {
        this.cartService = cartService;
        this.userService = userService;
        this.customerService = customerService;
        this.cartDetailsService = cartDetailsService;
    }

    /**
     * Получение текущей корзины со списком покупок, для отображения на странице
     *
     * @param cart Требуемая корзина
     * @return На страницу корзины
     */
    @Secured(value = "ROLE_CONSUMER")
    @GetMapping(value = "/cart")
    public String getFullCart(Cart cart, Model model) {

        model.addAttribute("cartDetail", new CartDetails());
        model.addAttribute("newCart", cartService.getCartWithCartDetails(cart));
        return "cart/cart";
    }

    /**
     * Ищет активную корщину текущего пользователя
     * Получает текущую, если она есть, или создает, если ее нет.
     * Добавляет в сессию пользователя, чтобы другие методы могли ее использовать и сократить
     * количество обращений к БД.
     *
     * @return Объект Корзина
     */
    @Secured(value = "ROLE_CONSUMER")
    @ModelAttribute(value = "cart")
    public Cart getOnlyCart() {
        return cartService.createOrGetCartByUserName(getCustomer());
    }

    /**
     * Получение текущего клиента
     *
     * @return Клиент
     */
    @Secured(value = "ROLE_CONSUMER")
    private Customer getCustomer() {
        User user = userService.getCurrentAuthUser();
        return customerService.getCustomerById(user.getId().toString());
    }

    /**
     * Rest
     * Добавление товара в корзину
     *
     * @param cartDetails Json покупки с isbn книги и количеством
     * @param cart        Объект корзины из сессии
     * @return В случае успеха возвращает количество добавленного товара или, в случае неуспеха,
     * доступное количество на складе.
     */
    @Secured(value = "ROLE_CONSUMER")
    @PostMapping(value = "cart", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> putInCart(@RequestBody CartDetails cartDetails, Cart cart) {
        cartDetails.setCartId(cart.getCartId());
        return ResponseEntity.ok(cartDetailsService.saveOrUpdateCartDetail(cartDetails));
    }

    /**
     * Обновление товара в корзине
     *
     * @param cartDetails Товар в корзине
     * @param model       Нужен для вывода на страницу доступного количества на складе, если его недостаточно
     * @return на страницу корзины
     */
    @Secured(value = "ROLE_CONSUMER")
    @PostMapping(value = "/count")
    public String updateQuantity(@ModelAttribute CartDetails cartDetails, Model model, Cart cart) {
        if (cartDetails == null) {
            log.warn("Получен некорреткный параметр cartDetails = NULL");
            return "error/error404";
        }
        int result = cartDetailsService.saveOrUpdateCartDetail(cartDetails);
        if (result < cartDetails.getCartDetailQuantity()) {
            model.addAttribute("available", result);
        }
        model.addAttribute("cartDetail", new CartDetails());
        model.addAttribute("newCart", cartService.getCartWithCartDetails(cart));

        return "cart/cart";
    }

    /**
     * Убрать из корзины
     *
     * @param book ISBN Удаляемого товара
     * @param cart Корзина, из которой нужно удалить
     * @return На страницу корзины
     */
    @Secured(value = "ROLE_CONSUMER")
    @PostMapping(value = "/takeaway", params = {"book"})
    public String deleteFromCart(String book, Cart cart) {
        CartDetails cartDetails = new CartDetails();
        cartDetails.setCartId(cart.getCartId());
        cartDetails.setCartDetailBook(book);
        cartDetailsService.deleteCartDetail(cartDetails);
        return "redirect:/cart";
    }
}
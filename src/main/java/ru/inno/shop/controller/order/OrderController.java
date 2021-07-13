package ru.inno.shop.controller.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.inno.shop.model.cart.Cart;
import ru.inno.shop.model.customer.Customer;
import ru.inno.shop.model.order.OrderStatus;
import ru.inno.shop.model.order.Orders;
import ru.inno.shop.model.user.User;
import ru.inno.shop.service.cart.CartService;
import ru.inno.shop.service.customer.CustomerService;
import ru.inno.shop.service.order.OrderService;
import ru.inno.shop.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Артём Матюнин
 * Контроллер для заказа
 */
@Slf4j
@Controller
@SessionAttributes(value = "cart")
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;
    private final UserService userService;
    private final CustomerService customerService;

    @Autowired
    public OrderController(OrderService orderService, CartService cartService,
                           UserService userService, CustomerService customerService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.userService = userService;
        this.customerService = customerService;
    }

    /**
     * Показать список заказов
     */
    @Secured({"ROLE_CONSUMER"})
    @GetMapping(value = "orders")
    public ModelAndView viewOrders() {
        User currentAuthUser = userService.getCurrentAuthUser();

        Set<Orders> set = orderService.getAllCustomerOrders(currentAuthUser.getId());
        List<Orders> orders = set.stream()
                .sorted((o1, o2) -> o2.getOrderNumber().compareTo(o1.getOrderNumber()))
                .collect(Collectors.toList());

        return new ModelAndView("order/orders", "orders", orders);
    }

    /**
     * Показать подробности заказа
     */
    @Secured({"ROLE_CONSUMER"})
    @GetMapping(value = "viewoneorder", params = {"orderNumber"})
    public ModelAndView getOneOrder(@RequestParam String orderNumber) {
        if (orderNumber == null||orderNumber.isEmpty()) {
            log.warn("Получен некоррретный параметр orderNumber = {}", orderNumber);
            return new ModelAndView("error/error404");
        }
        try {
            Orders order = orderService.getOrderById(Long.parseLong(orderNumber));
            return new ModelAndView("order/order", "order", order);
        }
        catch (NumberFormatException e){
            log.warn("Получен некоррретный параметр orderNumber {}", orderNumber);
            return new ModelAndView("error/error404");
        }

    }

    /**
     * Создание заказа
     */
    @Secured({"ROLE_CONSUMER"})
    @PostMapping(value = "createorder")
    public String createOrder(@ModelAttribute("cart") Cart cart, SessionStatus sessionStatus,  Model model) {
        if (cart == null) {
            log.warn("Получен некоррретный параметр cart = null");
            return "error/error404";
        }
        Cart cartToOrder = cartService.getCartWithCartDetails(cart);
        Orders order = orderService.createOrder(cartToOrder);
        model.addAttribute("order", order);
        model.addAttribute("customer", new Customer());
        sessionStatus.setComplete();
        return "order/order";
    }

    /**
     * Перейти к оплате
     */
    @Secured({"ROLE_CONSUMER"})
    @PostMapping(value = "checkout", params = {"orderNumber"})
    public void proceedToCheckout(@ModelAttribute Customer customer, @RequestParam String orderNumber,
                                    @RequestParam Float price, @RequestParam Float delivery, HttpServletResponse httpServletResponse) throws IOException {
        if (customer == null ) {
            log.warn("Получен некоррретный параметр customer = NULL. Праметр orderNumber = {}", orderNumber);
            return;
        }
        if (orderNumber == null || orderNumber.isEmpty()) {
            log.warn("Получен некоррретный параметр orderNumber = {}." +
                    " Параметр customer = {}", orderNumber, customer.getId());
            return;
        }
        long id = Long.parseLong(orderNumber);
        User user = userService.findById(customer.getId());
        user.setEmail(customer.getEmail());
        customer.setUser(user);
        customerService.updateCustomer(customer);

        Orders order = orderService.getOrderById(id);
        order.setStatus(OrderStatus.AWAITING_PAY);
        order.setPrice(price);
        order.setDelivery(delivery);
        orderService.updateOrder(order);

        StringBuilder builder = new StringBuilder();
        builder.append("http://localhost:80/index?");

        builder.append("shopName=").append("Bookshop");
        builder.append("&");
        builder.append("orderId=").append(order.getOrderNumber());
        builder.append("&");
        builder.append("paymentSum=").append(order.getPrice());

        httpServletResponse.sendRedirect(builder.toString());
    }
}
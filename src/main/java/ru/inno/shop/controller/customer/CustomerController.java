package ru.inno.shop.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.inno.shop.model.customer.Customer;
import ru.inno.shop.model.user.User;
import ru.inno.shop.service.customer.CustomerService;
import ru.inno.shop.service.user.UserService;

/**
 * Контроллер для страницы зарегистрированного пользователя
 *
 * @author Chernov Artem
 */
@Controller
@RequestMapping(value = "/account")
public class CustomerController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    /**
     * Получение сущности клиента по текущему пользователю
     */
    @GetMapping
    public String getCustomer(Model model) {

        User currentAuthUser = userService.getCurrentAuthUser();
        if (currentAuthUser == null) {
            return "error/error404";
        }

        String user_id = currentAuthUser.getId().toString();
        Customer customer = customerService.getCustomerById(user_id);
        customer.setEmail(customer.getUser().getEmail());

        model.addAttribute("customer", customer);
        return "customer/account";
    }

    /**
     * Сохранение измненений по клиенту в БД
     */
    @PostMapping
    public String saveCustomer(@ModelAttribute("customer") Customer customer, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "customer/account";
        }

        User user = userService.findById(customer.getId());
        if (bindingResult.hasErrors()) {
            return "customer/account";
        }

        user.setEmail(customer.getEmail());
        customer.setUser(user);

        customer = customerService.updateCustomer(customer);
        customer.setEmail(customer.getUser().getEmail());

        model.addAttribute("customer", customer);
        return "customer/account";
    }
}
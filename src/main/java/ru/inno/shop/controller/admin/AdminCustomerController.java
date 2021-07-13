package ru.inno.shop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.inno.shop.model.customer.Customer;
import ru.inno.shop.service.customer.CustomerService;
import java.util.List;

@Controller
public class AdminCustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Получение всех клиентов
     */
    @Secured(value="ROLE_ADMIN")
    @GetMapping(value = "/adminCustomer")
    public ModelAndView admin() {
        List<Customer> listCustomers = customerService.getAllCustomers();
        ModelAndView mav = new ModelAndView("admin/customer/adminCustomer");
        mav.addObject("listCustomers", listCustomers);
        return mav;
    }

    /**
     * Сохранение редиктирования
     * @param customer - измененный клиент
     */
    @PostMapping("/adminCustomerSave")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.addNewCustomer(customer);
        return "redirect:/adminCustomer";
    }

    /**
     * Получение сущности клиента для редактирование
     * @param id - идентификатор клиента
     */
    @GetMapping("/adminCustomerEdit")
    public ModelAndView editCustomerForm(@RequestParam String id) {
        ModelAndView mav = new ModelAndView("admin/customer/adminCustomerEdit");
        Customer customer = customerService.getCustomerById(id);
        mav.addObject("edit_customer", customer);
        return mav;
    }

    /**
     * Удаление клиента по его id
     * @param id - идентификатор клиента
     */
    @GetMapping("/adminCustomerDelete")
    public String deleteCustomerForm(@RequestParam String id) {
        customerService.deleteCustomer(id);
        return "redirect:/adminCustomer";
    }

    /**
     * Поиск клиентов по ключевому слову
     * @param keyword - ключевое слово по которому идет поиск
     */
    @GetMapping("/adminCustomerSearch")
    public ModelAndView search(@RequestParam String keyword) {
        List<Customer> result = customerService.search(keyword);
        ModelAndView mav = new ModelAndView("admin/customer/adminCustomerSearch");
        mav.addObject("listCustomers", result);
        return mav;
    }
}

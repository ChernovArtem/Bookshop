package ru.inno.shop.service.customer.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.inno.shop.model.customer.Customer;
import ru.inno.shop.repository.customer.CustomerDao;
import ru.inno.shop.service.cart.CartService;
import ru.inno.shop.service.customer.CustomerService;
import ru.inno.shop.service.order.OrderService;
import ru.inno.shop.service.wishlist.WishlistService;

import java.util.List;
import java.util.Optional;

/**
 * @author Chernov Artem
 */
@Slf4j
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private WishlistService wishlistService;

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.findAll();
    }

    @Override
    public Customer getCustomerById(String id) {
        Optional<Customer> optional = customerDao.findById(Long.valueOf(id));
        if (!optional.isPresent()) {
            log.warn("Customer by Id = {} is empty", id);
            return null;
        }
        return optional.get();
    }

    @Override
    public Customer addNewCustomer(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public void deleteCustomer(String id) {
        Customer customer = getCustomerById(id);
        if (customer == null) {
            return;
        }

        orderService.delete(customer);
        cartService.delete(customer);
        wishlistService.delete(customer);

        customerDao.delete(customer);
    }

    @Override
    public List<Customer> search(String keyword) {
        return customerDao.search(keyword);
    }
}

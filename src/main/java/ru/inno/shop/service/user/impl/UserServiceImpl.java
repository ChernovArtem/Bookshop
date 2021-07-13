package ru.inno.shop.service.user.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.inno.shop.model.customer.Customer;
import ru.inno.shop.model.wishlist.Wishlist;
import ru.inno.shop.repository.user.RoleDao;
import ru.inno.shop.repository.user.UserDao;
import ru.inno.shop.model.user.Role;
import ru.inno.shop.model.user.User;
import ru.inno.shop.service.customer.CustomerService;
import ru.inno.shop.service.user.UserService;
import ru.inno.shop.service.wishlist.WishlistService;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 *  Класс UserServiceImpl реализующий интерфейс UserService
 *
 *  Кодирует пароль пользователя, присваивает роль "Покупатель",
 *  сохраняет пользователя в БД.
 *  Находит пользователя в БД по имени.
 *
 * @author Kochetkov Viktor
 * @version 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    /** Сервис для кодировки паролей */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private WishlistService wishlistService;

    @Override
    public void saveCustomer(User user) {
        // получаем пароль и кодируем его
        String password = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(password);

        // каждый зарегистрированный пользователь получает роль "Покупатель" (3L)
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getById(3L));
        user.setRoles(roles);

        // покупателя записываем в таблицу клиентов
        Customer customer = new Customer();
        customer.setUser(user);
//        customer = customerService.addNewCustomer(customer);

        Wishlist wishlist = new Wishlist();
        wishlist.setCustomer(customer);
        wishlistService.addWishlist(wishlist);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        Optional<User> optional = userDao.findById(id);
        if (!optional.isPresent()) {
            log.warn("Не удалось найти пользователя по id = {}", id);
            return null;
        }
        return optional.get();
    }

    @Override
    public User getCurrentAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            log.warn("Не удалось получить текущего авторизованного пользователя");
            return null;
        }

        Object principal = auth.getPrincipal();
        org.springframework.security.core.userdetails.User user =
                (principal instanceof org.springframework.security.core.userdetails.User) ?
                        (org.springframework.security.core.userdetails.User) principal : null;

        return Objects.nonNull(user) ? findByUsername(user.getUsername()) : null;
    }

    @Override
    public void delete(Long id) {
        customerService.deleteCustomer(id.toString());
        User user = findById(id);
        if (user == null) {
            return;
        }
        userDao.deleteById(id);
    }

    @Override
    public List<User> search(String keyword) {
        return userDao.search(keyword);
    }

    @Override
    public void saveAdmin(User user) {
        saveWithRole(user, 1l);
    }

    @Override
    public void saveStorekeeper(User user) {
        saveWithRole(user, 4l);
    }

    private void saveWithRole(User user, Long role) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getById(role));
        user.getRoles();
        user.setRoles(roles);
        userDao.save(user);
    }

    @Override
    public void edit(User editUser) {
        User user = findById(editUser.getId());

        user.setUsername(editUser.getUsername());
        user.setEmail(editUser.getEmail());

        userDao.save(user);
    }
}

package ru.inno.shop.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.inno.shop.repository.user.UserDao;
import ru.inno.shop.model.user.Role;
import ru.inno.shop.model.user.User;
import java.util.HashSet;
import java.util.Set;

/**
 *  Класс UserDetailsServiceImpl реализующий интерфейс UserDetailsService
 *
 *  Создаем экземпляр пользователя, записываем его разрешения,
 *  возвращаем пользователя с именем, паролем и разрешением
 *
 * @author Kochetkov Viktor
 * @version 1.0
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // создаем экземпляр пользователя найденного по имени
        User user = userDao.findByUsername(username);

        // для разрешений выданных пользователю
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role : user.getRoles()) {
            // записываем все роли пользователя в список разрешений
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        // возвращаем пользователя с именем, паролем и разрешением
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}

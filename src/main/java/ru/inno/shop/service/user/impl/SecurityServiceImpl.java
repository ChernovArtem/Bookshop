package ru.inno.shop.service.user.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.inno.shop.service.user.SecurityService;

/**
 * Класс SecurityServiceImpl реализующий интерфейс SecurityService
 *
 * метод findLoggedInUsername() - поиск зарегистрированных пользователей
 * метод autoLogin() - для входа зарегистрированных пользователей
 *
 * @author Kochetkov Viktor
 * @version 1.0
 *
 */
@Slf4j
@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void autoLogin(String username, String password) {
        // получаем имя пользователя
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // создаем токен с именем пользователя, паролем и правами
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        // проверка пользователя
        authenticationManager.authenticate(authenticationToken);

        // если пользователь авторизован
        if (authenticationToken.isAuthenticated()) {
            //для пользователя устанавливается контекст безопасности
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            log.debug(String.format("%s, успешно выполнил вход", username));
        }
    }
}

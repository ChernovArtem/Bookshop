package ru.inno.shop.service.user;

public interface SecurityService {

    /**
     * Для входа зарегистрированных пользователей
     * @param username - логин
     * @param password - пароль
     */
    void autoLogin(String username, String password);
}

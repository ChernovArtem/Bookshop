package ru.inno.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.inno.shop.model.user.User;
import ru.inno.shop.service.user.UserService;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Контроллер главной страницы
 */
@Controller
public class Index {

    /** Сервис по работе с пользователями */
    @Autowired
    private UserService userService;

    /**
     * Переадресация на html-страницу
     * @return
     */
    @GetMapping(value = {"/", "/index"})
    public String start() {
        return "forward:/resources/index.html";
    }

    /**
     * Получение csrf токена для POST, PUT, DELETE запросов на html-страницах
     * @param request
     * @return
     */
    @GetMapping("/get_token")
    public ResponseEntity<String> getToken(HttpServletRequest request) {
        String token = null;
        if (request != null) {
            token = new HttpSessionCsrfTokenRepository().loadToken(request).getToken();
        }
        return Objects.nonNull(token) ? ResponseEntity.ok(token) : ResponseEntity.noContent().build();
    }

    /**
     * Получение текущего авторизованного пользователя,
     * для отображения на странице выпадающего меню по роли
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/current_auth_user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getCurrentAuthUser() {
        User user = userService.getCurrentAuthUser();
        return Objects.nonNull(user) ? ResponseEntity.ok(user) : ResponseEntity.noContent().build();
    }
}

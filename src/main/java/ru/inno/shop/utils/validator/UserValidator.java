package ru.inno.shop.utils.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.inno.shop.model.user.User;
import ru.inno.shop.service.user.UserService;
import java.util.regex.Pattern;

/**
 * Класс UserValidator реализующий интерфейс Validator
 *
 * проверяет форму авторизации и регистрации на правильность заполнения
 *
 * @author Kochetkov Viktor
 * @version 2.0
 *
 */
@Slf4j
@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    /** Паттерн для проверки логина */
    private final Pattern loginPattern = Pattern.compile("^[a-zA-Zа-яА-Я][a-zA-Zа-яА-Я0-9-_]+");

    /** Паттерн для проверки пароля. Допустимы спецсимволы (-_!@#&.+*) */
    private final Pattern loginPassword = Pattern.compile("[\\w@&#\\!\\+\\.\\*-]+");


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        if (!(o instanceof User)) {
            log.warn("Полученный класс не является User");
            return;
        }

        User user = (User) o;
        String username = user.getUsername();
        String userPassword = user.getPassword();

        if (user.getUsername().isEmpty()) {  // проверка на пустое поле
            errors.rejectValue("username", "Empty.userForm.username", "Поле не заполнено");
        } else if (!loginPattern.matcher(username).matches()) {  // проверка на символы в имени пользователя. Могут использоваться буквы, цифры и знаки _-
            errors.rejectValue("username", "Symbol.userForm.username", "Имя должно начинаться с буквы и содержать русские (латинские) буквы и цифры");
        } else if (user.getUsername().length() < 5 || user.getUsername().length() > 25) {  // проверка на длину имени пользователя
            errors.rejectValue("username", "Size.userForm.username", "В имени должно быть от 5 до 25 символов");
        } else if (userService.findByUsername(user.getUsername()) != null) {   // проверка на дубликат имени в БД
            errors.rejectValue("username", "Duplicate.userForm.username", "Tакое имя уже зарегестрировано");
        }

        if (errors.hasErrors()) {
            return;
        }

        if (user.getPassword().isEmpty()) {  // проверка на пустое поле
            errors.rejectValue("password", "Empty.userForm.password", "Поле не заполнено");
        } else if (!loginPassword.matcher(userPassword).matches()) {  // проверка пароля на символы
            errors.rejectValue("password", "Symbol.userForm.password", "В пароле использовались не допустимые символы");
        } else if (user.getPassword().length() < 6) {  // проверка длины пароля
            errors.rejectValue("password", "Size.userForm.password", "Пароль должен содержать больше 6 символов");
            return;
        }
        if (errors.hasErrors()) {
            return;
        }

        // проверка на совпадение паролей
        if (user.getConfirmPassword().isEmpty()) {
            errors.rejectValue("confirmPassword", "Empty.Different.userForm.password", "Поле не заполнено");
        } else if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password", "Пароль не совпадает");
        }
    }
}

package ru.inno.shop.controller.user;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.inno.shop.model.user.User;
import ru.inno.shop.service.user.SecurityService;
import ru.inno.shop.service.user.UserService;
import ru.inno.shop.utils.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    /**
     * Метод при открытии страницы регистрации
     */
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "user/registration";
    }

    /**
     * Метод регистрации нового пользователя
     * @param bindingResult - объект Spring, который содержит результат проверки и  ошибки, которые могли произойти
     */
    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        // передаем валидатору форму и перечень ошибок
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            // если ошибки были, то возвращаем пользователя на страницу регистрации

            StringBuilder errorsStr = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                if (errorsStr.length() > 0) {
                    errorsStr.append(";\n");
                }
                errorsStr.append(error.getDefaultMessage());
            }

            if (bindingResult.hasFieldErrors("username")) {
                userForm.setPassword("");
                userForm.setConfirmPassword("");
            }

            model.addAttribute("userForm", userForm);
            model.addAttribute("error", errorsStr.toString());
            return "user/registration";
        }

        //ошибок не было - сохраняем пользователя
        userService.saveCustomer(userForm);

        // логируем пользователя
        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        // и перенаправляем на главную страницу
        return "redirect:/index";
    }

    /**
     * Метод для авторизации пользователя
     */
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            // выдаем сообщение, при наличии ошибок при заполнении формы
            model.addAttribute("error", "Имя или пароль введены не корректно");
        }

        if (logout != null) {
            model.addAttribute("message", "");
        }

        return "user/login";
    }
}

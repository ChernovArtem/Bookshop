package ru.inno.shop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.inno.shop.model.user.User;
import ru.inno.shop.service.user.UserService;
import java.util.List;
import java.util.Map;

/**
 * Класс AdminUserController
 * контроллер для страницы Пользователи
 *
 */
@Controller
public class AdminUserController {

    @Autowired
    private UserService userService;

    /**
     * Получение всех пользователей
     */
    @GetMapping("/adminUsers")
    public ModelAndView admin() {
        List<User> listUsers = userService.findAll();
        ModelAndView mav = new ModelAndView("admin/user/adminUsers");
        mav.addObject("listUsers", listUsers);
        return mav;
    }

    /**
     * Получение страницы для добавления нового пользователя с ролью админ
     */
    @GetMapping("/adminAdminNew")
    public String newAdminForm(Map<String, Object> model) {
       User user = new User();
        model.put("newAdmin", user);
        return "admin/user/new_admin";
    }

    /**
     * Получение страницы для добавления нового пользователя с ролью кладовщик
     */
    @GetMapping("/adminStorekeeperNew")
    public String newStorekeeperForm(Map<String, Object> model) {
        User user = new User();
        model.put("newStorekeeper", user);
        return "admin/user/new_storekeeper";
    }

    /**
     * Сохранение пользователя при изменении
     */
    @PostMapping("/adminUsersSave")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.edit(user);
        return "redirect:/adminUsers";
    }

    /**
     * Добавление нового пользователя с ролью админ
     */
    @PostMapping("/newAdmin")
    public String saveAdmin(@ModelAttribute("user") User user) {
        userService.saveAdmin(user);
        return "redirect:/adminUsers";
    }

    /**
     * Добавление нового пользователя с ролью кладовщик
     */
    @PostMapping("/newStorekeeper")
    public String saveStorekeeper(@ModelAttribute("user") User user) {
        userService.saveStorekeeper(user);
        return "redirect:/adminUsers";
    }

    /**
     * Получение страницы для изменения пользователя
     */
    @GetMapping("/adminUsersEdit")
    public ModelAndView editUserForm(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("admin/user/adminUsersEdit");
        User user = userService.findById(id);
        mav.addObject("editUser", user);
        return mav;
    }

    /**
     * Удаление пользователя по его id
     */
    @GetMapping("/adminUsersDelete")
    public String deleteUserForm(@RequestParam long id) {
        userService.delete(id);
        return "redirect:/adminUsers";
    }

    /**
     * Поиск пользователей по ключевому слову
     * @param keyword - ключ по которому искать
     */
    @GetMapping("/adminUsersSearch")
    public ModelAndView search(@RequestParam String keyword) {
        List<User> users_result = userService.search(keyword);
        ModelAndView mav = new ModelAndView("admin/user/adminUsersSearch");
        mav.addObject("users_result", users_result);
        return mav;
    }
}

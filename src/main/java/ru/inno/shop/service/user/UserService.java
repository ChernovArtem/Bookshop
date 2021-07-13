package ru.inno.shop.service.user;

import ru.inno.shop.model.user.User;
import java.util.List;

public interface UserService {

    /**
     * Сохранение пользователя с ролью клиент в БД
     * @param user пользователя {@see ru.inno.shop.model.user.User}
     */
    void saveCustomer(User user);

    /**
     * Найти всех пользователей
     * @return список пользователей
     */
    List<User> findAll();

    /**
     * Получаем пользователя из БД по его имени
     * @param username - имя/логин пользователя
     * @return пользователя {@see ru.inno.shop.model.user.User}
     */
    User findByUsername(String username);

    /**
     * Получение пользователя по id
     * @param id - идентификатор пользователя
     * @return пользователь {@see ru.inno.shop.model.user.User}, если не нашел, то null
     */
    User findById(Long id);

    /**
     * Получение текущего авторизованного пользователя
     * @return авторизованного пользователя {@see ru.inno.shop.model.user.User},
     * если пользователь не авторизован, то null
     */
    User getCurrentAuthUser();

    /**
     * Удаление пользователя по его id
     * @param id - идентификатор пользователя
     */
    void delete(Long id);

    /**
     * Поиск пользователя по значению
     * @param keyword - значение для поиска
     * @return список найденных пользователей
     */
    List<User> search(String keyword);

    /**
     * Сохранение пользователя с ролью админ в БД
     * @param user - новый пользователь
     */
    void saveAdmin(User user);

    /**
     * Сохранение пользователя с ролью кладовщик в БД
     * @param user - новый пользователь
     */
    void saveStorekeeper(User user);

    /**
     * Редактирование пользователя
     * @param user - измененный пользователь
     */
    void edit(User user);
}

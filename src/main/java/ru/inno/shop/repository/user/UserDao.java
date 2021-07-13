package ru.inno.shop.repository.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.inno.shop.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {

    /**
     * Поиск пользователя по имени
     * @param username - имя пользователя
     * @return пользователь {@see ru.inno.shop.model.user.User}
     */
    User findByUsername(String username);

    /**
     * Поиск пользователя по значению
     * @param keyword - значение по которому искать
     * @return список пользователей
     */
    @Query(value = "select u from User u where "
            + "u.username like concat('%', :keyword, '%')"
            + " OR u.email like concat('%', :keyword, '%')")
    List<User> search(@Param("keyword") String keyword);
}
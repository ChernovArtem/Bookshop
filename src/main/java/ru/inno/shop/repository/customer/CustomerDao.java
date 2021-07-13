package ru.inno.shop.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.inno.shop.model.customer.Customer;
import java.util.List;

/**
 * Репозиторий для работы с сущностью зарегистрированного пользователя
 * @author Chernov Artem
 */
public interface CustomerDao extends JpaRepository <Customer, Long> {

    /**
     * Поиск клиента по значению
     * @param keyword - значение по которому стоит искать
     * @return Список клиентов {@see ru.inno.shop.model.customer.Customer}
     */
    @Query
            (value = "select c from Customer c where (c.firstname like concat('%', :keyword, '%'))"
                    + " OR c.lastname like concat('%', :keyword, '%')"
                    + " OR c.phone like concat('%', :keyword, '%')"
                    + " OR c.address like concat('%', :keyword, '%')"
                    + " OR c.street like concat('%', :keyword, '%')"
                    + " OR c.city like concat('%', :keyword, '%')"
                    + " OR c.state like concat('%', :keyword, '%')"
                    + " OR c.postal like concat('%', :keyword, '%')"
                    + " OR c.country like concat('%', :keyword, '%')")
    List<Customer> search(@Param("keyword") String keyword);
}

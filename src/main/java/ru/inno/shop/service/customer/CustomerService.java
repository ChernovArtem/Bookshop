package ru.inno.shop.service.customer;

import ru.inno.shop.model.customer.Customer;
import java.util.List;

/**
 * Сервис по работе с информацией зарегистрированного пользователя
 *
 * @author Chernov Artem
 */
public interface CustomerService {

    /**
     * Получение всех сущностей зарегистрированных пользователей
     *
     * @return
     */
    List<Customer> getAllCustomers();

    /**
     * Получение сущности зарегистрированного пользователя по его идентификатору
     *
     * @param id - идентификатор сущности зарегистрированного пользователя
     * @return
     */
    Customer getCustomerById(String id);

    /**
     * Запись сущности зарегистрированного пользователя
     *
     * @param customer - новая сущность зарегистрированного пользователя
     * @return сущность зарегистрированного пользователя
     */
    Customer addNewCustomer(Customer customer);

    /**
     * Обновление сущности зарегистрированного пользователя
     *
     * @param customer - измененная сущность зарегистрированного пользователя
     * @return сущность зарегистрированного пользователя
     */
    Customer updateCustomer(Customer customer);

    /**
     * Удаление сущности зарегистрированного пользователя
     *
     * @param id - идентификатор сущности зарегистрированного пользователя
     */
    void deleteCustomer(String id);

    /**
     * Поиск сущности зарегистрированного пользователя
     * @param keyword - значение для поиска
     * @return список найденных зарегистрированных пользователей
     */
    List<Customer> search(String keyword);
}
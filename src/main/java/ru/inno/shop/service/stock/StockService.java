package ru.inno.shop.service.stock;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.inno.shop.model.stock.Stock;

import java.util.List;

/**
 * Сервис по работе с информацией на складе
 *
 * @author Chernov Artem
 */
public interface StockService {

    /**
     * Поместить товар в резерв
     * @param isbn -  идентификатор книги
     */
    void placeGoodsInReserve(String isbn, int quantity);

    /**
     * Снять товар с резерва
     * @param isbn -  идентификатор книги
     */
    void removeGoodsFromReserve(String isbn);

    /**
     * Продажа товара
     * @param isbn -  идентификатор книги
     */
    void shipmentOfGoods(String isbn);


    /**
     * Поиск информации на складе по isbn книги
     * @param isbn -  идентификатор книги
     * @return информация со склада по книге
     */
    Stock getStockByISBN(String isbn);

    /**
     * Получение всех с сущностей склада
     * @return всехс сущностей склада
     */
    List<Stock> getAllStockBook();

    /**
     * Получение страницы с сущностями склада
     * @param pageable - класс с настройками страницы
     * @return страницу с сущностями склада
     */
    Page<Stock> getPageStockBook(Pageable pageable);

    /**
     * Сохранение изменения связанные со складом по книге
     * @param stock - сущность информации склада для книги
     * @return true, если обновился без ошибок
     */
    boolean addStock(Stock stock);

    /**
     * Сохранение изменения связанные со складом по книге
     * @param stock - сущность информации склада для книги
     * @return true, если обновился без ошибок
     */
    boolean saveStock(Stock stock);

    /**
     * Удаление сущности склада
     * @param stock - сущность информации склада для книги
     */
    void delete(Stock stock);
}

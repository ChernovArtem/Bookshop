package ru.inno.shop.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inno.shop.model.stock.Stock;

/**
 * Репозиторий для работы с сущностью склада
 * @author Chernov Artem
 */
public interface StockDao extends JpaRepository<Stock, String> {
}

package ru.inno.shop.controller.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.inno.shop.model.stock.Stock;
import ru.inno.shop.service.stock.StockService;
import java.util.Objects;

/**
 * Контроллер для работы со страницей кладовщика
 *
 * @author Chernov Artem
 */
@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    /**
     * Получение сущностей склада в виде страниц
     * @param page - страница
     * @param size - кол-во элементов на странице
     */
    @GetMapping(value = "/stock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Stock>> getStocks(@Param("page") int page, @Param("size") int size) {

        Pageable requestedPage = PageRequest.of(page, size);
        Page<Stock> stocks = stockService.getPageStockBook(requestedPage);

        return Objects.nonNull(stocks) ? ResponseEntity.ok(stocks) : ResponseEntity.noContent().build();
    }

    /**
     * Запись сущности склада в БД
     * @param stock - сущность склада
     */
    @PutMapping(value = "/stock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Stock> saveStock(@RequestBody Stock stock) {
        boolean result = stockService.saveStock(stock);
        return result ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}

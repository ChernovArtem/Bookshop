package ru.inno.shop.service.stock.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.inno.shop.model.stock.Stock;
import ru.inno.shop.repository.stock.StockDao;
import ru.inno.shop.service.stock.StockService;
import java.util.List;
import java.util.Optional;

/**
 * @author Chernov Artem
 */
@Slf4j
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockDao stockDao;

    @Override
    public void placeGoodsInReserve(String isbn, int quantity) {

        Stock stock = getStockByISBN(isbn);
        if (stock == null || quantity > stock.getAvailable()) {
            log.warn("Запрошенное количество {} товара {} больше, чем доступно {} или товар не существует",
                    quantity, isbn, stock.getAvailable());
            throw new IllegalArgumentException("Запрошенного товара не существует или недостотаточно на складе");
        }

        stock.setAvailable(stock.getAvailable() - quantity);
        stock.setReserve(stock.getReserve() + quantity);

        stockDao.save(stock);
    }

    @Override
    public void removeGoodsFromReserve(String isbn) {

        Stock stock = getStockByISBN(isbn);
        if (stock == null) {
            return;
        }

        stock.setReserve(stock.getReserve() - 1);
        stock.setAvailable(stock.getAvailable() + 1);

        stockDao.save(stock);
    }

    @Override
    public void shipmentOfGoods(String isbn) {

        Stock stock = getStockByISBN(isbn);
        if (stock == null) {
            return;
        }

        stock.setReserve(stock.getReserve() - 1);
        stock.setTotal(stock.getTotal() - 1);
        stock.setSold(stock.getSold() + 1);

        stockDao.save(stock);
    }

    @Override
    public Stock getStockByISBN(String isbn) {

        Optional<Stock> optional = stockDao.findById(isbn);
        if (!optional.isPresent()) {
            log.warn("Stock by ISBN = {} is empty", isbn);
            return null;
        }
        return optional.get();
    }

    @Override
    public List<Stock> getAllStockBook() {
        List<Stock> stockPage = stockDao.findAll();

        stockPage.forEach((stock) -> {
            String nameBook = stock.getBook().getTitle();
            stock.setNameBook(nameBook);
        });
        return stockPage;
    }

    @Override
    public Page<Stock> getPageStockBook(Pageable pageable) {
        Page<Stock> stockPage = stockDao.findAll(pageable);

        stockPage.forEach((stock) -> {
            String nameBook = stock.getBook().getTitle();
            stock.setNameBook(nameBook);
        });
        return stockPage;
    }

    @Override
    public boolean addStock(Stock stock) {
        stockDao.save(stock);
        return true;
    }

    @Override
    public boolean saveStock(Stock stock) {
        Stock stockEdit = getStockByISBN(stock.getIsbn());
        if (stockEdit == null || stock == null) {
            return false;
        }

        stockEdit.setSold(stock.getSold());
        stockEdit.setTotal(stock.getTotal());
        stockEdit.setReserve(stock.getReserve());
        stockEdit.setAvailable(stock.getAvailable());

        stockDao.save(stock);
        return true;
    }

    @Override
    public void delete(Stock stock) {
        stockDao.delete(stock);
    }
}

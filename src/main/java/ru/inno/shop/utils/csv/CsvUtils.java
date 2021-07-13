package ru.inno.shop.utils.csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.inno.shop.model.stock.Stock;
import ru.inno.shop.service.stock.StockService;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * Сервис/утилита по работе с csv-файлами
 *
 * @author Chernov Artem
 */
@Slf4j
@Service
public class CsvUtils {

    @Autowired
    private StockService stockService;

    /**
     * Выгрузка csv
     */
    public void exportCSV(HttpServletResponse response) {

        String filename = "stock.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        try {
            StatefulBeanToCsv<Stock> writer = new StatefulBeanToCsvBuilder<Stock>(response.getWriter())
                    .withQuotechar('\"')
                    .withSeparator(';')
                    .withOrderedResults(false)
                    .build();
            writer.write(stockService.getAllStockBook());
        } catch (IOException ex) {
            log.error("IOException", ex);
        } catch (CsvRequiredFieldEmptyException ex) {
            log.error("CsvRequiredFieldEmptyException", ex);
        } catch (CsvDataTypeMismatchException ex) {
            log.error("CsvDataTypeMismatchException", ex);
        }
    }

    /**
     *
     * @param file
     */
    public void uploadCSVFile(MultipartFile file) {
        if (file.isEmpty()) {
            return;
        }

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            CsvToBean<Stock> csvToBean = new CsvToBeanBuilder<Stock>(reader)
                    .withQuoteChar('\"')
                    .withSeparator(';')
                    .withType(Stock.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<Stock> stocks = csvToBean.parse();

            stocks.forEach((stock) -> stockService.saveStock(stock));
        } catch (IOException ex) {
            log.error("IOException", ex);
        }
    }
}

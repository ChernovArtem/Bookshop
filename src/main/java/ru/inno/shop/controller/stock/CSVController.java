package ru.inno.shop.controller.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.inno.shop.utils.csv.CsvUtils;
import javax.servlet.http.HttpServletResponse;

/**
 * Контроллер по работе с csv
 *
 * @author Chernov Artem
 */
@Controller
public class CSVController {

    @Autowired
    private CsvUtils csvUtils;

    /**
     * Выгрузка csv
     */
    @GetMapping(value = "/export-csv-file", produces="text/csv")
    public void exportCSV(HttpServletResponse response) {
        csvUtils.exportCSV(response);
    }

    /**
     * Загрузка csv
     */
    @PostMapping("/upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file) {
        csvUtils.uploadCSVFile(file);

        return "redirect:/storekeeper";
    }
}

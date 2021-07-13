package ru.inno.shop.utils.image;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageUtils {

    private final static String IMAGE_BOOKS_DIR = "resources/images/product/books-img";

    public void save(HttpServletRequest httpServletRequest, MultipartFile imagefile, String isbn) throws IOException {

        String uploadPath = httpServletRequest.getServletContext().getRealPath("") + IMAGE_BOOKS_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String imageName = imagefile.getOriginalFilename();
        String typeFile = imageName.substring(imageName.lastIndexOf('.'));

        Path newFile = Paths.get(uploadPath + File.separator + isbn + typeFile);
        Files.createDirectories(newFile.getParent());

        Files.write(newFile, imagefile.getBytes());
    }
}

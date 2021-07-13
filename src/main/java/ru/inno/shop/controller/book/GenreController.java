package ru.inno.shop.controller.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inno.shop.repository.book.GenreDao;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/genre")
public class GenreController {

    @Autowired
    private GenreDao genreDao;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Object[]>> getAllGenres() {

        List<Object[]> genres = genreDao.findGenreAndCount();
        return Objects.nonNull(genres) ? ResponseEntity.ok(genres) : ResponseEntity.noContent().build();
    }
}

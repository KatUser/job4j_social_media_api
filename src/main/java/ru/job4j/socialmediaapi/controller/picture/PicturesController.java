package ru.job4j.socialmediaapi.controller.picture;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.job4j.socialmediaapi.model.Picture;
import ru.job4j.socialmediaapi.service.picture.PictureService;

import java.util.List;

@Validated
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/pictures")
public class PicturesController {

    private final PictureService pictureService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Picture> getAllPictures() {
        return pictureService.getAllPictures();
    }
}

package ru.job4j.socialmediaapi.controller.picture;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.job4j.socialmediaapi.model.Picture;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.service.picture.PictureService;

@Validated
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/picture")
public class PictureController {

    private final PictureService pictureService;

    @PostMapping
    public ResponseEntity<Post> createPicture(@RequestBody Picture picture) {
        pictureService.savePicture(picture);
        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(picture.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(uri)
                .body(null);
    }
}

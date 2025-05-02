package ru.job4j.socialmediaapi.controller.picture;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.job4j.socialmediaapi.model.Picture;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.service.picture.PictureService;

@Tag(name = "PictureController", description = "Метод(ы) для взаимодействия с PictureController АПИ")
@Validated
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/picture")
public class PictureController {

    private final PictureService pictureService;

    @Operation(
            summary = "Создание фотографии",
            description = """
                    Создание фотографии.
                    При успешном выполнении метода возвращается код 201 с объектом типа Picture в теле ответа.""")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = Picture.class),
                    mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<Post> createPicture(@RequestBody Picture picture) {
        pictureService.savePicture(picture);
        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(picture.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}

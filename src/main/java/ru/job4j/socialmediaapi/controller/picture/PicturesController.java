package ru.job4j.socialmediaapi.controller.picture;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.job4j.socialmediaapi.model.Picture;
import ru.job4j.socialmediaapi.service.picture.PictureService;

import java.util.List;

@Tag(name = "PicturesController", description = "Метод(ы) для взаимодействия с PicturesController API")
@Validated
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/pictures")
public class PicturesController {

    private final PictureService pictureService;

    @Operation(
            summary = "Получение всех фотографий.",
            description = """
                    Получение всех фотографий в приложении.
                    При успешном выполнении метода возвращается код 200
                    и список с объектами типа Picture в теле ответа.""")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema
                    (implementation = Picture.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Picture> getAllPictures() {
        return pictureService.getAllPictures();
    }
}

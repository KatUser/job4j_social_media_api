package ru.job4j.socialmediaapi.validation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Violation {
    @NotBlank
    @Schema(description = "Поле, в котором нарушено правило валидации.",
            example = "name")
    private final String fieldName;

    @NotBlank
    @Schema(description = "Описание причины, по которой произошло нарушение правила валидации.",
            example = "name не должно быть пустым")
    private final String message;
}
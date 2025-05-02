package ru.job4j.socialmediaapi.validation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ValidationErrorResponse {
    @NotBlank
    @Schema(description = "Структура данных с сообщением об ошибке.",
            implementation = Violation.class)
    private final List<Violation> violations;
}
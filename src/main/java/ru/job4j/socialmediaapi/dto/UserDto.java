package ru.job4j.socialmediaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import ru.job4j.socialmediaapi.model.Post;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Id
    @NotBlank
    @EqualsAndHashCode.Include
    @Schema(description = "Идентификатор DTO пользователя", example = "1")
    private Long userId;

    @NotBlank
    @Schema(description = "имя DTO пользователя", example = "Имя")
    private String name;

    @Nullable
    private List<Post> posts;
}

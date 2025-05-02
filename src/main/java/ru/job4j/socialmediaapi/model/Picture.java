package ru.job4j.socialmediaapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "picture")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Schema(description = "Идентификатор фотографии.", example = "1")
    private Long id;

    @ManyToOne
    @NotNull
    @Schema(description = "Пост, за которым прикреплена фотография.",
            implementation = Post.class)
    private Post post;

    @NotNull
    @Schema(description = "Путь к фотографии",
            example = "localdir/path/photo.jpg")
    private String path;
}

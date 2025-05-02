package ru.job4j.socialmediaapi.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "post")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Schema(description = "Идентификатор поста", example = "1")
    private Long id;

    @ManyToOne
    @NotNull
    @Schema(description = "Объект пользователь",
            implementation = User.class)
    private User user;

    @NotNull
    @Schema(description = "Дата создания поста", example = "2023-10-15T15:15:15")
    private LocalDateTime created = LocalDateTime.now()
            .truncatedTo(ChronoUnit.SECONDS);

    @NotNull
    @Schema(description = "Заголовок поста", example = "Заголовок")
    @Pattern(regexp = "^\\S[\\w|\\W\\s]*$",
            message = """
                    заголовок должен состоять хотя бы из одного символа
                    и не начинаться с пробела""")
    private String title;

    @Nullable
    @Schema(description = "Дата регистрации", example = "Текст")
    @Pattern(regexp = "^\\S[\\w|\\W\\s]*$")
    private String text;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Picture> picture;
}

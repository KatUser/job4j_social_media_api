package ru.job4j.socialmediaapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Schema(description = "Идентификатор сообщения.", example = "1")
    private Long id;

    @NotNull
    @Schema(description = "Дата создания сообщения.", example = "2023-10-15T15:15:15")
    private LocalDateTime created = LocalDateTime.now()
            .truncatedTo(ChronoUnit.SECONDS);

    @ManyToOne
    @NotNull
    @Schema(description = "Пользователь, автор сообщения.",
    implementation = User.class)
    private User userFrom;

    @ManyToOne
    @NotNull
    @Schema(description = "Пользователь, получатель сообщения.",
            implementation = User.class)
    private User userTo;

    @NotNull
    @Schema(description = "Пользователь, автор сообщения.",
            implementation = String.class)
    private String text;
}

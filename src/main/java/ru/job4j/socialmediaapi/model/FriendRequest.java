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
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Schema(description = "Идентификатор запроса в друзья.", example = "1")
    private Long id;

    @NotNull
    @Schema(description = "Дата создания запроса в друзья.", example = "2023-10-15T15:15:15")
    private LocalDateTime created = LocalDateTime.now()
            .truncatedTo(ChronoUnit.SECONDS);

    @ManyToOne
    @NotNull
    @Schema(description = "Пользователь, инициатор запроса в друзья.",
            implementation = User.class)
    private User userFrom;

    @ManyToOne
    @NotNull
    @Schema(description = "Пользователь, адресат запроса в друзья.",
            implementation = User.class)
    private User userTo;

    @NotNull
    @Schema(description = "Признак принятия запроса в друзья",
            example = "true")
    private boolean accepted = false;

}

package ru.job4j.socialmediaapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "activity_feed")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class ActivityFeed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Schema(description = "Идентификатор новости.", example = "1")
    private Long id;

    @NotNull
    @Schema(description = "Дата создания новости.", example = "2023-10-15T15:15:15")
    private LocalDateTime created = LocalDateTime.now()
            .truncatedTo(ChronoUnit.SECONDS);

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @Schema(description = "Пользователь, автор новости.",
            implementation = User.class)
    private User user;

    @Nullable
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @Schema(description = "Список постов в новости.",
            implementation = Post.class)
    private List<Post> post;
}

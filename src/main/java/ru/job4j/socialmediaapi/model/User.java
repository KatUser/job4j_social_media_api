package ru.job4j.socialmediaapi.model;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "app_user")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Schema(description = "Идентификатор пользователя", example = "1")
    private Long id;

    @NotNull
    @Schema(description = "Дата регистрации", example = "2023-10-15T15:15:15")
    private LocalDateTime registered = LocalDateTime.now()
            .truncatedTo(ChronoUnit.SECONDS);

    @NotBlank(message = "поле name не может быть пустым")
    @Length(min = 1,
            message = "name должно быть не менее 1 символа")
    @Schema(description = "имя пользователя", example = "name")
    private String name;

    @NotBlank(message = "поле password не может быть пустым")
    @Length(min = 1,
            message = "поле password должно быть не менее 1 символа")
    @Schema(description = "пароль", example = "password@1")
    private String password;

    @NotNull
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "поле email должно содержать валидный адрес электронной почты")
    @Schema(description = "электронная почта", example = "email@example.com")
    private String email;

    @Nullable
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Schema(description = "список подписчиков",
            implementation = User.class)
    private List<User> subscriber;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "app_user_role", joinColumns = @JoinColumn(name = "app_user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> role = new HashSet<>();
}

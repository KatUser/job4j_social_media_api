package ru.job4j.socialmediaapi.model;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
    private Long id;

    private LocalDateTime registered = LocalDateTime.now()
            .truncatedTo(ChronoUnit.SECONDS);

    @Pattern(regexp = "^[A-Za-z]{3,20}$",
    message = """
            имя пользователя должно быть не менее 3 и не более 20 знаков,
            состоять только из латинских букв
            """)
    private String name;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–_[{}]:;',?/*~$^+=<>])\\S{6,20}$",
            message = """
                    пароль должен быть не менее 6 и не более 20 знаков,
                    состоять из латинских букв,
                    не содержать пробел,
                    содержать хотя бы одну цифру
                    хотя бы одну букву в нижнем регистре
                    хотя бы одну букву в верхнем регистре
                    и хотя бы один спецсимвол (!@#&()–_[{}]:;',?/*~$^+=<>)
                    """)
    private String password;

    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "поле email должно содержать валидный адрес электронной почты")
    private String email;

    @Nullable
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<User> subscriber;

}

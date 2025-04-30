package ru.job4j.socialmediaapi.model;

import com.fasterxml.jackson.annotation.*;
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
@Table(name = "post")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    private User user;

    private LocalDateTime created = LocalDateTime.now()
            .truncatedTo(ChronoUnit.SECONDS);

    @Pattern(regexp = "^\\S[\\w|\\W\\s]*$",
            message = """
                    заголовок должен состоять хотя бы из одного символа
                    и не начинаться с пробела""")
    private String title;

    private String text;

    @Nullable
    @OneToMany(fetch = FetchType.EAGER)
    private List<Picture> picture;
}

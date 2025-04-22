package ru.job4j.socialmediaapi.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;

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

    private LocalDateTime created;

    private String title;

    private String text;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Picture> picture;
}

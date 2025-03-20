package ru.job4j.socialmediaapi.model;

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
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @ManyToOne
    private User user;

    private LocalDateTime created;

    private String title;

    private String text;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Picture> picture;
}

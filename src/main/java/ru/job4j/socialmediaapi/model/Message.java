package ru.job4j.socialmediaapi.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private int id;

    private LocalDateTime created;

    @ManyToMany
    @JoinColumn(name = "user_id")
    private User userFrom;

    @ManyToMany
    @JoinColumn(name = "user_id")
    private User userTo;

    private String text;
}

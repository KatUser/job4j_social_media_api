package ru.job4j.socialmediaapi.model;

import lombok.*;

import jakarta.persistence.*;
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

    @ManyToOne
    private User userFrom;

    @ManyToOne
    private User userTo;

    private String text;
}

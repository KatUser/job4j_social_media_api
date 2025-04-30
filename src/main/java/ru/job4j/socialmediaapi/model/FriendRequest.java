package ru.job4j.socialmediaapi.model;

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
    private int id;

    private LocalDateTime created = LocalDateTime.now()
            .truncatedTo(ChronoUnit.SECONDS);

    @ManyToOne
    private User userFrom;

    @ManyToOne
    private User userTo;

    private boolean accepted = false;

}

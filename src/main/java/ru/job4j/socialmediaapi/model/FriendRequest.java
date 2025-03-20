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
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private LocalDateTime created;

    @ManyToMany(fetch = FetchType.EAGER)
    private User userFrom;

    @ManyToMany(fetch = FetchType.EAGER)
    private User userTo;

    private boolean accepted = false;

}

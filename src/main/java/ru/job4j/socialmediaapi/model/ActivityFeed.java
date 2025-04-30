package ru.job4j.socialmediaapi.model;

import jakarta.persistence.*;
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
    private int id;

    private LocalDateTime created = LocalDateTime.now()
            .truncatedTo(ChronoUnit.SECONDS);

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Post> post;
}

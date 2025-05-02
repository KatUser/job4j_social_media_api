package ru.job4j.socialmediaapi.repository.activityfeed;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.socialmediaapi.model.ActivityFeed;

public interface ActivityFeedRepository extends JpaRepository<ActivityFeed, Long> {
}

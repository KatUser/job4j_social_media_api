package ru.job4j.socialmediaapi.repository.activityfeed;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.socialmediaapi.model.ActivityFeed;

public interface ActivityFeedRepository extends CrudRepository<ActivityFeed, Integer> {
}

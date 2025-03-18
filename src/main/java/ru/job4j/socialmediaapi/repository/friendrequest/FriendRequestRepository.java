package ru.job4j.socialmediaapi.repository.friendrequest;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.socialmediaapi.model.FriendRequest;

public interface FriendRequestRepository extends CrudRepository<FriendRequest, Integer> {
}

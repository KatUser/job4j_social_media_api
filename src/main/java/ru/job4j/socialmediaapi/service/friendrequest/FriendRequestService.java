package ru.job4j.socialmediaapi.service.friendrequest;

import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmediaapi.model.FriendRequest;

public interface FriendRequestService {

    @Transactional
    void acceptFriendRequest(FriendRequest friendRequest);

    @Transactional
    void rejectFriendRequest(FriendRequest friendRequest);

    @Transactional
    void deleteFromFriends(Integer userId, Integer friendId);

    @Transactional
    void sendFriendRequest(FriendRequest friendRequest);
}

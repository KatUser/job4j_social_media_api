package ru.job4j.socialmediaapi.service.friendrequest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmediaapi.model.FriendRequest;
import ru.job4j.socialmediaapi.repository.friendrequest.FriendRequestRepository;

@AllArgsConstructor
@Service
public class FriendRequestServiceDB implements FriendRequestService {

    private final FriendRequestRepository friendRequestRepository;

    /* Принять запрос в друзья */
    @Override
    @Transactional
    public void acceptFriendRequest(FriendRequest friendRequest) {
        friendRequestRepository.acceptFriendRequest(friendRequest);
        friendRequest.setAccepted(true);
        var userTo = friendRequest.getUserTo();
        var userFrom = friendRequest.getUserFrom();
        userTo.getSubscriber().add(userFrom);
        userFrom.getSubscriber().add(userTo);
    }

    /* Отклонить запрос в друзья */
    @Override
    @Transactional
    public void rejectFriendRequest(FriendRequest friendRequest) {
        var user = friendRequest.getUserFrom();
        user.getSubscriber().add(friendRequest.getUserTo());
    }

    /* Если один из друзей удаляет другого из друзей,
    то он также отписывается. Второй пользователь при этом должен остаться подписчиком.
    */
    @Override
    @Transactional
    public void deleteFromFriends(Integer userId, Integer friendId) {
        friendRequestRepository.deleteFromFriends(userId);
        var friendRequest = friendRequestRepository.findById(userId).get();
        friendRequest.getUserTo().getSubscriber().remove(friendRequest.getUserFrom());

    }

    @Override
    @Transactional
    public void sendFriendRequest(FriendRequest friendRequest) {
        friendRequestRepository.save(friendRequest);
    }

}

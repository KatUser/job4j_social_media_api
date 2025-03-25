package ru.job4j.socialmediaapi.repository.friendrequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.socialmediaapi.model.FriendRequest;
import ru.job4j.socialmediaapi.model.User;
import ru.job4j.socialmediaapi.repository.activityfeed.ActivityFeedRepository;
import ru.job4j.socialmediaapi.repository.post.PostRepository;
import ru.job4j.socialmediaapi.repository.user.UserRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FriendRequestRepositoryTest {

    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Autowired
    private ActivityFeedRepository activityFeedRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    public void setUp() {
        activityFeedRepository.deleteAll();
        userRepository.deleteAll();
        postRepository.deleteAll();
        friendRequestRepository.deleteAll();
    }

    @Test
    public void whenAddFriendRequest_thenUserGetsFriendRequest() {
        var timeNow = LocalDateTime.now();
        var user = new User();
        user.setEmail("test@email.com");
        user.setPassword("password");
        user.setName("John Doe");
        user.setRegistered(timeNow);
        user.setSubscriber(null);
        userRepository.save(user);

        var user2 = new User();
        user2.setEmail("test2@email.com");
        user2.setPassword("password2");
        user2.setName("John Doe");
        user2.setRegistered(timeNow);
        user2.setSubscriber(null);
        userRepository.save(user2);

        var friendRequest = new FriendRequest();
        friendRequest.setCreated(timeNow);
        friendRequest.setUserFrom(user);
        friendRequest.setUserTo(user2);
//        friendRequest.setAccepted(false);
        friendRequestRepository.save(friendRequest);
        assertThat(friendRequestRepository.count()).isEqualTo(1);
        assertThat(friendRequest.getUserFrom()).isEqualTo(user);
        assertThat(friendRequest.getUserTo()).isEqualTo(user2);

    }

}
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

    private User user;

    private User user2;

    private FriendRequest friendRequest;

    private final LocalDateTime timeNow = LocalDateTime.now();


    @BeforeEach
    public void setUp() {
        activityFeedRepository.deleteAll();
        userRepository.deleteAll();
        postRepository.deleteAll();
        friendRequestRepository.deleteAll();

        user = new User();
        user.setEmail("test@email.com");
        user.setPassword("password");
        user.setName("John Doe");
        user.setRegistered(timeNow);
        user.setSubscriber(null);
        userRepository.save(user);

        user2 = new User();
        user2.setEmail("test2@email.com");
        user2.setPassword("password2");
        user2.setName("John Doe");
        user2.setRegistered(timeNow);
        user2.setSubscriber(null);
        userRepository.save(user2);

        friendRequest = new FriendRequest();
        friendRequest.setCreated(timeNow);
        friendRequest.setUserFrom(user);
        friendRequest.setUserTo(user2);
        friendRequestRepository.save(friendRequest);
    }

    @Test
    public void whenSaveFriendRequest_thenCanGetItById() {
        var friendRequestOptional = friendRequestRepository.findById(friendRequest.getId());

        assertThat(friendRequestRepository.count()).isEqualTo(1);
        assertThat(friendRequest.getUserFrom()).isEqualTo(user);
        assertThat(friendRequest.getUserTo()).isEqualTo(user2);
        assertThat(friendRequestOptional).get().isEqualTo(friendRequest);
    }


    @Test
    public void whenSaveFriendRequestAndDelete_thenCannotGetItById() {
        friendRequestRepository.delete(friendRequest);
        var friendRequestOptional = friendRequestRepository.findById(friendRequest.getId());

        assertThat(friendRequestOptional).isEmpty();
        assertThat(friendRequestRepository.count()).isEqualTo(0);
    }

    @Test
    public void whenSaveFriendRequestAndDeleteById_thenCannotGetItById() {
        friendRequestRepository.deleteById(friendRequest.getId());
        var friendRequestOptional = friendRequestRepository.findById(friendRequest.getId());

        assertThat(friendRequestOptional).isEmpty();
        assertThat(friendRequestRepository.count()).isEqualTo(0);
    }

    @Test
    public void whenSaveFriendRequests_thenCanGetItFromRepository() {
        assertThat(friendRequestRepository.findAll()).asList().containsExactly(friendRequest);
    }
}
package ru.job4j.socialmediaapi.repository.activityfeed;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.socialmediaapi.model.ActivityFeed;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.User;
import ru.job4j.socialmediaapi.repository.post.PostRepository;
import ru.job4j.socialmediaapi.repository.user.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ActivityFeedRepositoryTest {

    @Autowired
    private ActivityFeedRepository activityFeedRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    private final LocalDateTime timeNow = LocalDateTime.now();

    @BeforeEach
    public void setUp() {
        activityFeedRepository.deleteAll();
        userRepository.deleteAll();
        postRepository.deleteAll();
    }


    @Test
    public void whenSaveActivityFeed_thenCanGetItById() {
//        var timeNow = LocalDateTime.now();

        var user = new User();
        user.setEmail("test@email.com");
        user.setPassword("password");
        user.setName("John Doe");
        user.setRegistered(timeNow);
        user.setSubscriber(null);
        userRepository.save(user);

        var post = new Post();
        post.setUser(user);
        post.setCreated(timeNow);
        post.setTitle("Test Post");
        post.setText("Test Text");
        post.setPicture(null);
        postRepository.save(post);

        var activityFeed = new ActivityFeed();
        activityFeed.setCreated(timeNow);
        activityFeed.setUser(user);
        activityFeed.setPost(List.of(post));

        activityFeedRepository.save(activityFeed);
        var activityFeedFound = activityFeedRepository.findById(activityFeed.getId());
        assertThat(activityFeedFound).isPresent();
        assertThat(activityFeedFound.get().getId()).isEqualTo(activityFeed.getId());
    }

    @Test
    public void wheSaveActivityFeedAndDeleteIt_thenCannotGetItById() {
//        var timeNow = LocalDateTime.now();

        var user = new User();
        user.setEmail("test@email.com");
        user.setPassword("password");
        user.setName("John Doe");
        user.setRegistered(timeNow);
        user.setSubscriber(null);
        userRepository.save(user);

        var post = new Post();
        post.setUser(user);
        post.setCreated(timeNow);
        post.setTitle("Test Post");
        post.setText("Test Text");
        post.setPicture(null);
        postRepository.save(post);

        var activityFeed = new ActivityFeed();
        activityFeed.setCreated(timeNow);
        activityFeed.setUser(user);
        activityFeed.setPost(List.of(post));

        activityFeedRepository.save(activityFeed);
        activityFeedRepository.delete(activityFeed);

        var activityFeedFound = activityFeedRepository.findById(activityFeed.getId());

        assertThat(activityFeedFound).isEmpty();
        assertThat(activityFeedRepository.existsById(activityFeed.getId())).isFalse();
        assertThat(activityFeedRepository.count()).isEqualTo(0);
    }

    @Test
    public void wheSaveActivityFeedAndDeleteItById_thenCannotGetItById() {
//        var timeNow = LocalDateTime.now();

        var user = new User();
        user.setEmail("test@email.com");
        user.setPassword("password");
        user.setName("John Doe");
        user.setRegistered(timeNow);
        user.setSubscriber(null);
        userRepository.save(user);

        var post = new Post();
        post.setUser(user);
        post.setCreated(timeNow);
        post.setTitle("Test Post");
        post.setText("Test Text");
        post.setPicture(null);
        postRepository.save(post);

        var activityFeed = new ActivityFeed();
        activityFeed.setCreated(timeNow);
        activityFeed.setUser(user);
        activityFeed.setPost(List.of(post));

        activityFeedRepository.save(activityFeed);
        activityFeedRepository.deleteById(activityFeed.getId());

        var activityFeedFound = activityFeedRepository.findById(activityFeed.getId());

        assertThat(activityFeedFound).isEmpty();
        assertThat(activityFeedRepository.existsById(activityFeed.getId())).isFalse();
        assertThat(activityFeedRepository.count()).isEqualTo(0);
    }

}

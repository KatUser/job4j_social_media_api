package ru.job4j.socialmediaapi.repository.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.User;
import ru.job4j.socialmediaapi.repository.user.UserRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        postRepository.deleteAll();
    }

    @Test
    public void whenSavePost_ThenCanGetPostById() {
        var timeNow = LocalDateTime.now();

        var user = new User();
        user.setEmail("test@email.com");
        user.setPassword("password");
        user.setName("John Doe");
        user.setRegistered(timeNow);
        userRepository.save(user);

        var post = new Post();
        post.setTitle("Post Title");
        post.setText("Post Text");
        post.setCreated(timeNow);
        post.setUser(user);
        postRepository.save(post);

        var postOptional = postRepository.findById(post.getId());
        assertThat(postOptional.isPresent()).isTrue();
        assertThat(postRepository.findById(postOptional.get().getId()).get()).isEqualTo(post);
        assertThat(postRepository.count()).isEqualTo(1);
    }

    @Test
    public void whenSavePostAndDeleteIt_ThenCannotGetPostById() {
        var timeNow = LocalDateTime.now();

        var user = new User();
        user.setEmail("test@email.com");
        user.setPassword("password");
        user.setName("John Doe");
        user.setRegistered(timeNow);
        userRepository.save(user);

        var post = new Post();
        post.setTitle("Post Title");
        post.setText("Post Text");
        post.setCreated(timeNow);
        post.setUser(user);
        postRepository.save(post);
        postRepository.delete(post);

        var postOptional = postRepository.findById(post.getId());

        assertThat(postOptional).isEmpty();
        assertThat(postRepository.count()).isEqualTo(0);
    }

    @Test
    public void whenSavePostAndDeleteItById_ThenCannotGetPostById() {
        var timeNow = LocalDateTime.now();

        var user = new User();
        user.setEmail("test@email.com");
        user.setPassword("password");
        user.setName("John Doe");
        user.setRegistered(timeNow);
        userRepository.save(user);

        var post = new Post();
        post.setTitle("Post Title");
        post.setText("Post Text");
        post.setCreated(timeNow);
        post.setUser(user);
        postRepository.save(post);
        postRepository.deleteById(post.getId());

        var postOptional = postRepository.findById(post.getId());

        assertThat(postOptional).isEmpty();
        assertThat(postRepository.count()).isEqualTo(0);
        assertThat(postRepository.existsById(post.getId())).isFalse();
    }
}
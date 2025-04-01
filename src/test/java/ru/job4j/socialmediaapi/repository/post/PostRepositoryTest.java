package ru.job4j.socialmediaapi.repository.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.User;
import ru.job4j.socialmediaapi.repository.user.UserRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    private Post post;

    private final LocalDateTime timeNow = LocalDateTime.now();

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        postRepository.deleteAll();

        var user = new User();
        user.setEmail("test@email.com");
        user.setPassword("password");
        user.setName("John Doe");
        user.setRegistered(timeNow);
        userRepository.save(user);

        post = new Post();
        post.setTitle("Post Title");
        post.setText("Post Text");
        post.setCreated(timeNow);
        post.setUser(user);
        postRepository.save(post);
    }

    @Test
    public void whenSavePost_ThenCanGetPostById() {
        var postOptional = postRepository.findById(post.getId());

        assertThat(postOptional.get()).isEqualTo(post);
        assertThat(postRepository.count()).isEqualTo(1);
    }

    @Test
    public void whenSavePostAndDeleteIt_ThenCannotGetPostById() {
        postRepository.delete(post);
        var postOptional = postRepository.findById(post.getId());

        assertThat(postOptional).isEmpty();
        assertThat(postRepository.count()).isEqualTo(0);
        assertThat(postRepository.existsById(post.getId())).isFalse();
    }

    @Test
    public void whenSavePostAndDeleteItById_ThenCannotGetPostById() {
        postRepository.deleteById(post.getId());

        var postOptional = postRepository.findById(post.getId());

        assertThat(postOptional).isEmpty();
        assertThat(postRepository.count()).isEqualTo(0);
        assertThat(postRepository.existsById(post.getId())).isFalse();
    }
}
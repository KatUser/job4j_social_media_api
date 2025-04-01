package ru.job4j.socialmediaapi.repository.picture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmediaapi.model.Picture;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.User;
import ru.job4j.socialmediaapi.repository.post.PostRepository;
import ru.job4j.socialmediaapi.repository.user.UserRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PictureRepositoryTest {

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    private Picture picture;

    private final LocalDateTime timeNow = LocalDateTime.now();

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        postRepository.deleteAll();
        pictureRepository.deleteAll();

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

        picture = new Picture();
        picture.setPost(post);
        picture.setPath("testpath");
        pictureRepository.save(picture);
    }

    @Test
    public void whenSavePicture_thenCanGetPictureById () {
        var pictureOptional = pictureRepository.findById(picture.getId());

        assertThat(pictureOptional.get()).isEqualTo(picture);
        assertThat(pictureRepository.count()).isEqualTo(1);
        assertThat(pictureRepository.existsById(picture.getId())).isTrue();
    }

    @Test
    public void whenSavePictureAndDelete_thenCannotGetPictureById () {
        pictureRepository.delete(picture);
        var pictureOptional = pictureRepository.findById(picture.getId());

        assertThat(pictureRepository.count()).isEqualTo(0);
        assertThat(pictureRepository.existsById(picture.getId())).isFalse();
        assertThat(pictureOptional).isEmpty();
    }

    @Test
    public void whenSavePictureAndDeleteItById_thenCannotGetPictureById () {
        pictureRepository.deleteById(picture.getId());
        var pictureOptional = pictureRepository.findById(picture.getId());

        assertThat(pictureRepository.count()).isEqualTo(0);
        assertThat(pictureRepository.existsById(picture.getId())).isFalse();
        assertThat(pictureOptional).isEmpty();
    }

}
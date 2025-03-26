package ru.job4j.socialmediaapi.repository.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.socialmediaapi.model.User;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void whenSaveUser_thenCanFindItById() {
        var timeNow = LocalDateTime.now();
        var user = new User();
        user.setEmail("test@email.com");
        user.setPassword("password");
        user.setName("John Doe");
        user.setRegistered(timeNow);
        userRepository.save(user);
        var userFound = userRepository.findById(user.getId());
        assertThat(userFound).isPresent();
        assertThat(userFound.get().getName()).isEqualTo(user.getName());
    }

    @Test
    public void whenSaveUsers_thenCanFindAll() {
        var user1 = new User();
        user1.setEmail("test@email.com");
        user1.setPassword("password");
        user1.setName("John Doe");
        var user2 = new User();
        user2.setEmail("test2@email.com");
        user2.setPassword("password2");
        user2.setName("John Doe");
        userRepository.save(user1);
        userRepository.save(user2);
        var usersFound = userRepository.findAll();
        assertThat(usersFound).asList().contains(user1, user2);
    }

    @Test
    public void whenSaveUserAndDeleteById_thenCannotFindItById() {
        var user = new User();
        user.setEmail("test@email.com");
        user.setPassword("password");
        user.setName("John Doe");
        user.setRegistered(LocalDateTime.now());
        userRepository.save(user);
        userRepository.deleteById(user.getId());
        var userFound = userRepository.findById(user.getId());
        assertThat(userFound).isEmpty();
    }

}
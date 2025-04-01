package ru.job4j.socialmediaapi.repository.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmediaapi.model.User;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    LocalDateTime timeNow = LocalDateTime.now();

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        user = new User();
        user.setEmail("test@email.com");
        user.setPassword("password");
        user.setName("John Doe");
        user.setRegistered(timeNow);
        userRepository.save(user);
    }

    @Test
    public void whenSaveUser_thenCanFindItById() {
        var userFound = userRepository.findById(user.getId());
        assertThat(userFound).isPresent();
        assertThat(userFound.get().getName()).isEqualTo(user.getName());
        assertThat(userRepository.count()).isEqualTo(1);
    }

    @Test
    public void whenSaveUsers_thenCanFindAll() {
        var user2 = new User();
        user2.setEmail("test2@email.com");
        user2.setPassword("password2");
        user2.setName("John Doe");
        userRepository.save(user2);
        var usersFound = userRepository.findAll();
        assertThat(usersFound).asList().contains(user, user2);
    }

    @Test
    public void whenSaveUserAndDeleteById_thenCannotFindItById() {
        userRepository.deleteById(user.getId());
        var userFound = userRepository.findById(user.getId());
        assertThat(userFound).isEmpty();
        assertThat(userRepository.count()).isEqualTo(0);
    }

    @Test
    public void whenSaveUserAndDeleteIt_thenCannotFindItById() {
        userRepository.delete(user);
        var userFound = userRepository.findById(user.getId());
        assertThat(userFound).isEmpty();
        assertThat(userRepository.count()).isEqualTo(0);
    }

}
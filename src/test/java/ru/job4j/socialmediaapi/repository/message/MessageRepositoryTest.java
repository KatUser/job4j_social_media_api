package ru.job4j.socialmediaapi.repository.message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmediaapi.model.Message;
import ru.job4j.socialmediaapi.model.User;
import ru.job4j.socialmediaapi.repository.user.UserRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MessageRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    private Message message;

    private final LocalDateTime timeNow = LocalDateTime.now();

    @BeforeEach
    public void setUp() {
        messageRepository.deleteAll();
        userRepository.deleteAll();

        User user = new User();
        user.setEmail("test@email.com");
        user.setPassword("password");
        user.setName("John Doe");
        user.setRegistered(timeNow);
        user.setSubscriber(null);
        userRepository.save(user);

        User user2 = new User();
        user2.setEmail("test2@email.com");
        user2.setPassword("password2");
        user2.setName("John Doe");
        user2.setRegistered(timeNow);
        user2.setSubscriber(null);
        userRepository.save(user2);

        message = new Message();
        message.setCreated(timeNow);
        message.setText("Test message");
        message.setUserTo(user);
        message.setUserFrom(user2);
        messageRepository.save(message);
    }

    @Test
    public void whenSaveMessage_ThenCanGetMessageById() {
        var messageOptional = messageRepository.findById(message.getId());

        assertThat(messageOptional.get()).isEqualTo(message);
        assertThat(messageRepository.existsById(message.getId())).isTrue();

    }

    @Test
    public void whenSaveMessageAndDeleteIt_ThenCannotGetMessageById() {
        messageRepository.delete(message);

        var messageOptional = messageRepository.findById(message.getId());

        assertThat(messageOptional).isEmpty();
        assertThat(messageRepository.existsById(message.getId())).isFalse();
        assertThat(messageRepository.count()).isEqualTo(0);

    }

    @Test
    public void whenSaveMessageAndDeleteItById_ThenCannotGetMessageById() {
        messageRepository.deleteById(message.getId());

        var messageOptional = messageRepository.findById(message.getId());

        assertThat(messageOptional).isEmpty();
        assertThat(messageRepository.existsById(message.getId())).isFalse();
        assertThat(messageRepository.count()).isEqualTo(0);
    }

}
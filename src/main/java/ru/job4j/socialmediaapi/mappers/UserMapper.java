package ru.job4j.socialmediaapi.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.socialmediaapi.dto.UserDto;
import ru.job4j.socialmediaapi.model.User;
import ru.job4j.socialmediaapi.repository.post.PostRepository;

@Service
@AllArgsConstructor
public class UserMapper {

    private final PostRepository postRepository;

    public UserDto getUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getId());
        userDto.setName(user.getName());
        userDto.setPosts(postRepository.findByUser(user));
        return userDto;
    }
}

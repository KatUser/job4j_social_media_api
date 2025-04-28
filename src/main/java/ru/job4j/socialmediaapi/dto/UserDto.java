package ru.job4j.socialmediaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.job4j.socialmediaapi.model.Post;

import java.util.List;

/*
Список из Объектов DTO (userId, username, posts),
где posts - это список публикаций этого пользователя. /
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    Long userId;

    String name;

    List<Post> posts;
}

package ru.job4j.socialmediaapi.repository.post;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.socialmediaapi.model.Post;

public interface PostRepository extends CrudRepository<Post, Integer> {
}

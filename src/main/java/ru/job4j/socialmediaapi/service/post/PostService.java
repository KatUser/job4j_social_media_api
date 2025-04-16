package ru.job4j.socialmediaapi.service.post;

import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmediaapi.model.Picture;
import ru.job4j.socialmediaapi.model.Post;

public interface PostService {

    @Transactional
    void createPost(Post post, Picture picture);

    @Transactional
    void updatePostById(String title, String text, Picture picture, Integer postId);

    @Transactional
    void deletePostById(Integer postId);
}

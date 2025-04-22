package ru.job4j.socialmediaapi.service.post;

import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmediaapi.model.Picture;
import ru.job4j.socialmediaapi.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    @Transactional
    void createPost(Post post, Picture picture);

    @Transactional
    boolean updatePostById(Long postId, String title,
                           String text,
                           Picture picture
                           );

    @Transactional
    boolean deletePostById(Long postId);

    @Transactional
    Post savePost(Post post);

    Optional<Post> getPostById(Long postId);

    List<Post> getAllPosts();
}

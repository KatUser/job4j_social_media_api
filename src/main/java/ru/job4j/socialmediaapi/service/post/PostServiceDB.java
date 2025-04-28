package ru.job4j.socialmediaapi.service.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.repository.post.PostRepository;
import ru.job4j.socialmediaapi.service.picture.PictureService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PostServiceDB implements PostService {

    private final PostRepository postRepository;

    private final PictureService pictureService;

    @Override
    @Transactional
    public void createPost(Post post) {
        post.getPicture().forEach(pictureService::savePicture);
        postRepository.save(post);

    }

    @Override
    @Transactional
    public boolean deletePostById(Long postId) {
        return postRepository.deletePostById(postId) > 0L;
    }

    @Override
    @Transactional
    public boolean updatePost(Post post) {
        post.getPicture().forEach(pictureService::savePicture);
        return postRepository.updatePost(post) > 0L;
    }

    @Override
    @Transactional
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Optional<Post> getPostById(Long postId) {
        return postRepository.findById(postId);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

}

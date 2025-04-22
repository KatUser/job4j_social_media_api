package ru.job4j.socialmediaapi.service.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmediaapi.model.Picture;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.repository.picture.PictureRepository;
import ru.job4j.socialmediaapi.repository.post.PostRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PostServiceDB implements PostService {

    private final PostRepository postRepository;

    private final PictureRepository pictureRepository;

    @Override
    @Transactional
    public void createPost(Post post, Picture picture) {
        postRepository.save(post);
        picture.setPost(post);
        pictureRepository.save(picture);
    }

    @Override
    @Transactional
    public boolean deletePostById(Long postId) {
        return postRepository.deletePostById(postId) > 0L;
    }

    @Override
    @Transactional
    public boolean updatePostById(Long postId,
                                  String title,
                                  String text,
                                  Picture picture
                                  ) {
        pictureRepository.save(picture);
        postRepository.findById(postId).get().getPicture().add(picture);
        return postRepository.updatePostTitleAndText(postId, title, text) > 0L;
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

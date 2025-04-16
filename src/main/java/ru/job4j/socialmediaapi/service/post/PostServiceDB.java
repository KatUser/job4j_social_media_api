package ru.job4j.socialmediaapi.service.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmediaapi.model.Picture;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.repository.picture.PictureRepository;
import ru.job4j.socialmediaapi.repository.post.PostRepository;

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
    public void deletePostById(Integer postId) {
        postRepository.deletePostById(postId);
    }

    @Override
    @Transactional
    public void updatePostById(String title,
                           String text,
                           Picture picture,
                           Integer postId) {
        postRepository.updatePostTitleAndTextAndPicture(title, text, picture, postId);
        picture.setPost(postRepository.findById(postId).get());
        pictureRepository.deleteById(picture.getId());
        pictureRepository.save(picture);
    }
}

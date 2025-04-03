package ru.job4j.socialmediaapi.repository.post;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.User;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findAllByUser(User user);

    List<Post> findAllByCreated(LocalDateTime created);

    Page<Post> findAllOrderByCreatedDesc(Pageable pageable);
}

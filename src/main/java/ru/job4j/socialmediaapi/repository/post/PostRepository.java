package ru.job4j.socialmediaapi.repository.post;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUser(User user);

    List<Post> findByCreatedBetween(LocalDateTime end, LocalDateTime start);

    Page<Post> findByOrderByCreatedDesc(Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query(value = """
            update Post post set post.title = :#{#post.title},
                        post.text = :#{#post.title}
                    where post.id = :#{#post.id}
            """)
    int updatePost(@Param("post") Post post);

    @Modifying(clearAutomatically = true)
    @Query(value = """
                delete from Picture as pic where pic.id in(
                    select id from Picture where pic.post.id = :id
                    )
            """)
    int deletePostPictures(@Param("id") Integer id);

    @Modifying(clearAutomatically = true)
    @Query(value = """
            delete from Post as post
                       where post.id = :id
            """)
    int deletePostById(@Param("id") Long id);

    @Query("""
            select post from Post as post
                    join User as user on post.user.id = user.id
                            where user.id in (select user.id
                                    from FriendRequest as friendRequest
                                    where friendRequest.userTo = :user)
                                                order by post.created desc
            """)
    Page<Post> findUserSubscibersPostsSortedByCreatedDesc(@Param("user") User user,
                                                          Pageable pageable);

    @Query("""
        select post from Post as post where post.user.id = :userId
        """
    )
    Post findPostByUserOrderByCreatedDesc(@Param("userId") Long userId);
}

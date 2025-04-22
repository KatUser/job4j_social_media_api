package ru.job4j.socialmediaapi.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmediaapi.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
            select user from User as user
                        where user.email = :email and
                                    user.password = :password
            """)
    Optional<User> findUserByEmailAndPassword(@Param("email") String email,
                                              @Param("password") String password);

    @Query("""
             select user.subscriber from User as user
             where user = :user
            """)
    List<User> findAllUserSubscribers(@Param("user") User user);

    @Query("""
            select user from User as user
            join FriendRequest as friendRequest
            on user.id = friendRequest.userFrom.id
                        where friendRequest.accepted = true
                        and friendRequest.userTo = :user
            """)
    List<User> findAllAcceptedFriendRequests(@Param("user") User user);

    @Query
            ("""
             select user.subscriber from User as user
             where user = :user
            """)
    List<User> findAllUserFriends(@Param("user") User user);

    @Transactional
    @Modifying
    @Query("delete from User as user where user.id = :userId")
    int delete(@Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query("""
            update User as user
            set user.name = :#{#user.name},
            user.email = :#{#user.email},
            user.password = :#{#user.password}
            where user.id =:#{#user.id}
            """)
    int update(@Param("user") User user);
}

package ru.job4j.socialmediaapi.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.job4j.socialmediaapi.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

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
    List<User> findAllUserFriends(@Param("user") User user);

}

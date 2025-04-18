package ru.job4j.socialmediaapi.repository.friendrequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.job4j.socialmediaapi.model.FriendRequest;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Integer> {

    @Modifying(clearAutomatically = true)
    @Query(value = """
                update FriendRequest as friendRequest
                            set friendRequest.accepted = false
                                        where friendRequest.userFrom.id = :userFrom
            """)
    int deleteFromFriends(@Param("userFrom")Integer userFrom);

    @Modifying(clearAutomatically = true)
    @Query(value = """
                update FriendRequest as friendRequest
                            set friendRequest.accepted = true
                                        where friendRequest.id =:friendRequestId
            """)
    int acceptFriendRequest(@Param("friendRequest") FriendRequest friendRequestId);

}


package dev.skyherobrine.project.backend.repositories.mongodb;

import dev.skyherobrine.project.backend.models.mongodb.Friend;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends MongoRepository<Friend, Long> {

    @Query(value = "{'id': {$exists: true}}", fields = "{'id': 1}")
    Optional<Long> getMaxCurrentId();

    List<Friend> findBySender_IdOrRecipient_IdOrderByIdAsc(Long senderId, Long recipientId);

    Optional<Friend> findBySender_IdAndRecipient_Id(Long senderId, Long recipientId);
}

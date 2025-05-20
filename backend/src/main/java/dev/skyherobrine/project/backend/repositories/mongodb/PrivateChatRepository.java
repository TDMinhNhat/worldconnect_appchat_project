package dev.skyherobrine.project.backend.repositories.mongodb;

import dev.skyherobrine.project.backend.models.mongodb.Friend;
import dev.skyherobrine.project.backend.models.mongodb.PrivateChat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateChatRepository extends MongoRepository<PrivateChat, Friend> {
}

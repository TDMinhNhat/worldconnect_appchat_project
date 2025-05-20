package dev.skyherobrine.project.backend.repositories.mongodb;

import dev.skyherobrine.project.backend.keys.GroupMemberKey;
import dev.skyherobrine.project.backend.models.mongodb.GroupMember;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMemberRepository extends MongoRepository<GroupMember, GroupMemberKey> {
}

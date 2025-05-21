package dev.skyherobrine.project.backend.models.mongodb;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.skyherobrine.project.backend.enums.GroupMemberRole;
import dev.skyherobrine.project.backend.enums.GroupMemberStatus;
import dev.skyherobrine.project.backend.keys.GroupMemberKey;
import dev.skyherobrine.project.backend.models.mariadb.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document(collection = "GroupMembers")
@Getter @Setter
@NoArgsConstructor
public class GroupMember {

    @MongoId
    private GroupMemberKey id;

    private GroupMemberRole role;

    @Field(name = "user_response_join")
    private User userResponseJoin;

    @Field(name = "user_ban")
    private User userBan;

    @Field(name = "date_created")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @Field(name = "date_updated")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateUpdated;

    private GroupMemberStatus status;

    public GroupMember(GroupMemberKey id, GroupMemberRole role, GroupMemberStatus status) {
        this.id = id;
        this.role = role;
        this.status = status;
        this.userResponseJoin = this.userBan = null;
        this.dateCreated = this.dateUpdated = LocalDateTime.now();
    }
}

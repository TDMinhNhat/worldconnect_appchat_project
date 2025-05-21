package dev.skyherobrine.project.backend.models.mongodb;

import dev.skyherobrine.project.backend.models.mariadb.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document(collection = "GroupChats")
@Getter @Setter
@NoArgsConstructor
public class GroupChat {
    @MongoId
    private Long id;

    private Group group;

    private User owner;

    private String message;

    private String messageImageURL;

    private String messageVideoURL;

    private String messageAudioURL;

    @Field(name = "date_created")
    private LocalDateTime dateCreated;

    @Field(name = "date_updated")
    private LocalDateTime dateUpdated;

    public GroupChat(Long id, Group group, User owner, String message, String messageImageURL, String messageVideoURL, String messageAudioURL) {
        this.id = id;
        this.group = group;
        this.owner = owner;
        this.message = message;
        this.messageImageURL = messageImageURL;
        this.messageVideoURL = messageVideoURL;
        this.messageAudioURL = messageAudioURL;
        this.dateCreated = this.dateUpdated = LocalDateTime.now();
    }
}

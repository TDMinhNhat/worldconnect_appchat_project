package dev.skyherobrine.project.backend.models.mongodb;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.skyherobrine.project.backend.models.mariadb.User;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document(collection = "PrivateChats")
@Getter @Setter
@NoArgsConstructor
public class PrivateChat {

    @MongoId @Field(name = "friend_id")
    @NotNull(message = "The friend id must be required")
    private Friend friendId;

    @NotNull(message = "The sender can't be null")
    private User sender;

    private String message;

    private String messageImageURL;

    private String messageVideoURL;

    private String messageAudioURL;

    private boolean recall;

    private boolean read;

    @Field(name = "date_created")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @Field(name = "date_updated")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateUpdated;

    public PrivateChat(Friend friendId, User sender, String message, String messageImageURL, String messageVideoURL, String messageAudioURL) {
        this.friendId = friendId;
        this.sender = sender;
        this.message = message;
        this.messageImageURL = messageImageURL;
        this.messageVideoURL = messageVideoURL;
        this.messageAudioURL = messageAudioURL;
        this.recall = this.read = false;
        this.dateCreated = this.dateUpdated = LocalDateTime.now();
    }
}

package dev.skyherobrine.project.backend.models.mongodb;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.skyherobrine.project.backend.enums.FriendStatus;
import dev.skyherobrine.project.backend.models.mariadb.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document(collection = "Friends")
@Getter @Setter
@NoArgsConstructor
public class Friend {

    @MongoId
    @NotNull(message = "The friendship id can't be null")
    @NotBlank(message = "The friendship id can't be blank")
    private Long id;

    @NotNull(message = "The sender add friend can't be null")
    private User sender;

    @NotNull(message = "The recipient add friend can't be null")
    private User recipient;

    @Field(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Field(name = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    private FriendStatus status;

    public Friend(Long id, User sender, User recipient) {
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
        this.status = FriendStatus.PENDING;
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }
}

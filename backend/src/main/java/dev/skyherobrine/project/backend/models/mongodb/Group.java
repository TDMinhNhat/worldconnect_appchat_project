package dev.skyherobrine.project.backend.models.mongodb;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.skyherobrine.project.backend.models.mariadb.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document(collection = "Groups")
@Getter @Setter
@NoArgsConstructor
public class Group {
    @MongoId
    @NotNull(message = "The group id is required!")
    @NotBlank(message = "The group id can't be blank or empty")
    private Long id;

    @NotNull(message = "The group must have a name!")
    @NotBlank(message = "The group name can't be blank or empty")
    @Size(max = 100, message = "The group name can't be longer than 100 characters")
    private String name;

    private String description;

    private boolean waitingResponse;

    @Field(name = "date_created")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @Field(name = "date_updated")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateUpdated;

    public Group(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.waitingResponse = true;
        this.dateCreated = this.dateUpdated = LocalDateTime.now();
    }
}

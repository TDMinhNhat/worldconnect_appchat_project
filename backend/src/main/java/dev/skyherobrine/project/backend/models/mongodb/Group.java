package dev.skyherobrine.project.backend.models.mongodb;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private Long id;

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

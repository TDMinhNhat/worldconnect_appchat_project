package dev.skyherobrine.project.backend.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class FriendDTO {

    @NotBlank(message = "The sender can't be blank. Please input the sender id")
    @Min(value = 1, message = "The sender id must be greater than 0")
    private Long senderId;

    @NotBlank(message = "The receiver can't be blank. Please input the receiver id")
    @Min(value = 1, message = "The receiver id must be greater than 0")
    private Long receiverId;

    @Data
    @NoArgsConstructor @AllArgsConstructor
    public class FriendStatusDTO {

        @NotBlank(message = "The sender can't be blank. Please input the sender id")
        @Min(value = 1, message = "The sender id must be greater than 0")
        private Long firstUserId;

        @NotBlank(message = "The receiver can't be blank. Please input the receiver id")
        @Min(value = 1, message = "The receiver id must be greater than 0")
        private Long secondUserId;

        @Pattern(regexp = "^(ACCEPTED|REJECTED|BLOCKED|DELETED)$", message = "The status must be one of the following: ACCEPTED, REJECTED, BLOCKED and DELETED")
        private String status;
    }
}

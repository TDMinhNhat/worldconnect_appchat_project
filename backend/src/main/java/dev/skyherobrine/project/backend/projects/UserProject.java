package dev.skyherobrine.project.backend.projects;

import dev.skyherobrine.project.backend.enums.UserStatus;
import dev.skyherobrine.project.backend.models.mariadb.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserProject {
    private Long id;
    private String firstName;
    private String lastName;
    private boolean sex;
    private String phone;
    private String address;
    private String email;
    private String username;
    private UserRole role;
    private boolean accountVerified;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserStatus status;
}

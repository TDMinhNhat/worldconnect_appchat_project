package dev.skyherobrine.project.backend.models.mariadb;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Table(name = "UserRoles")
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class UserRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name", nullable = false, unique = true, length = 100) @NonNull
    @NotNull(message = "The role name can't be null")
    @NotBlank(message = "The role name can't be empty")
    private String roleName;

    @Column(nullable = false)
    private boolean status;

    @Column(name = "date_created", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @PrePersist
    public void prePersist() {
        status = true;
    }
}

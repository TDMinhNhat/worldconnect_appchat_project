package dev.skyherobrine.project.backend.models.mariadb;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Table(name = "UserRoles")
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class UserRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String roleName;

    @Column(nullable = false)
    private boolean status;

    @Column(name = "date_created", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @PrePersist
    public void prePersist() {
        this.dateCreated = LocalDateTime.now();
        this.status = true;
    }
}

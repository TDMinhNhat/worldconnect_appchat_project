package dev.skyherobrine.project.backend.dtos;

import dev.skyherobrine.project.backend.models.mariadb.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class UserRoleDTO {

    @NotNull(message = "The role name can't be null")
    @NotBlank(message = "The role name can't be empty or blank")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "The role name can only contain the letters, spaces and not empty or blank")
    private String roleName;

    public UserRole toObject() {
        return new UserRole(roleName);
    }
}

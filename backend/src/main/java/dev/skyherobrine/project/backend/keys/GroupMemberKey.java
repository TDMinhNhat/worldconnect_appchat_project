package dev.skyherobrine.project.backend.keys;

import dev.skyherobrine.project.backend.models.mariadb.User;
import dev.skyherobrine.project.backend.models.mongodb.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class GroupMemberKey {
    private Group group;
    private User user;
}

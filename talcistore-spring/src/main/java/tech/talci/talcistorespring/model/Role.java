package tech.talci.talcistorespring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String description;

    public static Role of(String roleName, String roleDescription) {
        Role role = new Role();
        role.setName(roleName);
        role.setDescription(roleDescription);
        return role;
    }

    public static boolean isAdmin(User user) {
        for (Role role: user.getRoles()) {
            if (role.getName().equals("ADMIN")) {
                return true;
            }
        }

        return false;
    }
}

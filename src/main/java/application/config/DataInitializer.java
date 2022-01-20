package application.config;

import application.model.Role;
import application.model.User;
import application.service.RoleService;
import application.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setName(Role.RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setName(Role.RoleName.USER);
        roleService.add(userRole);
        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setPassword("admin");
        user.setRoles(Set.of(adminRole));
        userService.add(user);
    }
}

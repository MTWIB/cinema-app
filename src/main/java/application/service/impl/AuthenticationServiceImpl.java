package application.service.impl;

import application.model.Role;
import application.model.User;
import application.service.AuthenticationService;
import application.service.RoleService;
import application.service.ShoppingCartService;
import application.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final RoleService roleService;

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.getRoles().add(roleService.getRoleByName(Role.RoleName.USER.name()));
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}

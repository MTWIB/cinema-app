package application.security;

import application.model.User;
import application.service.UserService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userFromDb = userService.findByEmail(email);
        User user = userFromDb.orElseThrow(
                () -> new UsernameNotFoundException("Can't find"
                        + " user with following email: " + email));
        UserBuilder builder =
                org.springframework.security.core.userdetails.User.withUsername(email);
        builder.password(user.getPassword());
        builder.roles(user.getRoles()
                .stream()
                .map(role -> role.getName().name())
                .toArray(String[]::new));
        return builder.build();
    }
}

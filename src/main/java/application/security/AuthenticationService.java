package application.security;

import application.exception.AuthenticationException;
import application.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}

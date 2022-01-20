package application.service;

import application.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}

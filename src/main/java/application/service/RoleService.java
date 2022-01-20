package application.service;

import application.model.Role;

public interface RoleService {
    Role add(Role role);

    Role getRoleByName(String roleName);
}

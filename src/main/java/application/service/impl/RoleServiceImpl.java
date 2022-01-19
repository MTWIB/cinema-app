package application.service.impl;

import application.dao.RoleDao;
import application.model.Role;
import application.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Override
    public Role add(Role role) {
        return roleDao.add(role);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleDao.getByName(roleName)
                .orElseThrow(() -> new RuntimeException("Can't find following role " + roleName));
    }
}

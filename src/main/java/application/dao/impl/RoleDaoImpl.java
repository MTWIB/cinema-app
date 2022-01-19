package application.dao.impl;

import application.dao.AbstractDao;
import application.dao.RoleDao;
import application.exception.DataProcessingException;
import application.model.Role;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    public RoleDaoImpl(SessionFactory factory) {
        super(factory, Role.class);
    }

    @Override
    public Optional<Role> getByName(String roleName) {
        try (Session session = factory.openSession()) {
            Query<Role> query = session.createQuery(
                    "FROM Role WHERE name = :name", Role.class);
            query.setParameter("name", Role.RoleName.valueOf(roleName));
            return query.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find following role " + roleName, e);
        }
    }
}

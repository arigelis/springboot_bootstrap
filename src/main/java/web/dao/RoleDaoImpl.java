package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> allRoles() {
        return entityManager.createQuery("from Role").getResultList();
    }

    @Override
    @Transactional
    public void add(Role role) {
        entityManager.persist(role);
    }

    @Override
    @Transactional
    public void delete(Role role) {
        entityManager.remove(entityManager.contains(role) ? role : entityManager.merge(role));
    }

    @Override
    @Transactional
    public void edit(Role role) {
        entityManager.merge(role);
    }

    @Override
    @Transactional
    public Role getById(long id) {
        return entityManager.find(Role.class, id);
    }
}


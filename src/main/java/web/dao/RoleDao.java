package web.dao;

import web.model.Role;
import java.util.List;

public interface RoleDao {
    List allRoles();

    void add(Role role);

    void delete(Role role);

    void edit(Role role);

    Role getById(long id);

    Role getRoleByName(String name);
}

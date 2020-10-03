package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.RoleDao;
import web.model.Role;
import web.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleServiceImpl {// implements RoleService
    @Autowired
    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    public List<User> allRoles() {
        return roleDao.allRoles();
    }

    @Transactional
    public void add(Role role) {
        roleDao.add(role);
    }

    @Transactional
    public void delete(Role role) {
        roleDao.delete(role);
    }

    @Transactional
    public void edit(Role role) {
        roleDao.edit(role);
    }

    @Transactional
    public Role getById(long id) {
        return roleDao.getById(id);
    }

}

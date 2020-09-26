package web.dao;


import web.model.User;

import java.util.List;

public interface UserDao {
    User getUserByName(String name);
    List allUsers();
    void add(User user);
    void delete(User user);
    void edit(User user);
    User getById(long id);
}

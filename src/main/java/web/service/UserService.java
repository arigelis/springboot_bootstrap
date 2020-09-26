package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> allUsers();

    void add(User user);

    void delete(User user);

    void edit(User user);

    User getById(long id);
}
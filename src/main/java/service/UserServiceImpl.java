package service;

import dao.RoleDao;
import dao.UserDao;
import entities.Roles;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by maxbacinskiy on 28.02.17.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    public void registerUser(User user) {
        user.getRoles().add(roleDao.getRoleByName(Roles.USER));
        userDao.registerUser(user);
    }

    public User getUserByLogin(String login) {
        return userDao.getUserByName(login);
    }
}

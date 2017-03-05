package dao;

import entities.User;

/**
 * Created by maxbacinskiy on 28.02.17.
 */
public interface UserDao {
    public void registerUser(User user);

    public User getUserByName(String username);
}

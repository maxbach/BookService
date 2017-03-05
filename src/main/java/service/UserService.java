package service;

import entities.User;

/**
 * Created by maxbacinskiy on 28.02.17.
 */
public interface UserService {
    public void registerUser(User user);

    public User getUserByLogin(String login);
}

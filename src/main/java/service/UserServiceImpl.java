package service;

import dao.UserDao;
import entities.Role;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by maxbacinskiy on 28.02.17.
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;


    public void registerUser(User user) {
        user.setRole(Role.USER);
        userDao.registerUser(user);
    }

    public User getUserByLogin(String login) {
        return userDao.getUserByName(login);
    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.getUserByName(s);

        if (user == null) {
            throw new UsernameNotFoundException("User details not found with this username: " + s);
        }
        Set<GrantedAuthority> authoritySet = new HashSet<GrantedAuthority>();
        authoritySet.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User
                (user.getLogin(), user.getPassword(), authoritySet);
    }
}

package dao;

import entities.Role;
import entities.Roles;
import entities.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by maxbacinskiy on 28.02.17.
 */

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private RoleDao roleDao;

    private boolean isAdminAdded = false;

    @Transactional
    public void registerUser(User user) {
        checkAdmin();
        sessionFactory.getCurrentSession().save(user);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public User getUserByName(String username) {
        checkAdmin();
        List<User> users = sessionFactory.getCurrentSession().createQuery("from User where login=:login").
                setParameter("login", username).list();
        if (users.size() == 0) {
            return null;
        } else {
            return users.get(0);
        }
    }

    @Transactional
    private void checkAdmin() {
        if (!isAdminAdded) {
            if (getAllUsers().size() == 0) {
                Set<Role> roleSet = new HashSet<Role>();
                roleSet.add(roleDao.getRoleByName(Roles.ADMIN));
                User admin = new User("admin", "pass", roleSet); // isn't perfect
                sessionFactory.getCurrentSession().save(admin);
            }
            isAdminAdded = true;
        }
    }

    @SuppressWarnings("unchecked")
    private List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").getResultList();
    }


}

package dao;

import entities.Role;
import entities.Roles;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by maxbacinskiy on 01.03.17.
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    private boolean isGood = false;

    @Transactional
    @SuppressWarnings("unchecked")
    public Role getRoleByName(Roles role) {
        if (!isGood) {
            List<Role> roles = sessionFactory.getCurrentSession().createQuery("from Role").getResultList();
            if (roles.size() == 0) {
                for (Roles role1 : Roles.values()) {
                    sessionFactory.getCurrentSession().save(new Role(role1.getName()));
                }
            }
            isGood = true;
        }
        return (Role) sessionFactory.getCurrentSession().createQuery("from Role where name=:name").
                setParameter("name", role.getName()).getSingleResult();
    }
}

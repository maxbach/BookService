package dao;

import entities.Role;
import entities.Roles;

/**
 * Created by maxbacinskiy on 01.03.17.
 */
public interface RoleDao {
    public Role getRoleByName(Roles name);
}

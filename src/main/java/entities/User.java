package entities;

import javax.persistence.*;

/**
 * Created by maxbacinskiy on 28.02.17.
 */
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_login", unique = true)
    private String login;

    @Column(name = "user_password")
    private String password;

    @Transient
    private String confirmPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Role role;

    public User() {

    }

    public User(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

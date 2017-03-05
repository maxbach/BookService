package entities;


import javax.persistence.*;

/**
 * Created by maxbacinskiy on 28.02.17.
 */
@Entity
@Table(name = "ROLE")
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private int id;

    @Column(name = "role_name")
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

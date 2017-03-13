package entities;

/**
 * Created by maxbacinskiy on 01.03.17.
 */
public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    DBA("ROLE_DBA"),
    ANONYMOUS("ROLE_ANONYMOUS");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

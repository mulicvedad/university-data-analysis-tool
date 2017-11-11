package ba.unsa.etf.bp.udat.models;

import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Edin on 11.11.2017..
 */

@Entity
public class User extends BaseModel {

    private String firstname;
    private String lastname;
    private String username;
    private String passwordHash;
    private String email;
    private Timestamp createdAt;
    private Role role;

    public User(String firstname, String lastname, String username, String passwordHash, String email, Timestamp createdAt, Role role)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.createdAt = createdAt;
        this.role = role;
    }
    public User() {}

    @Basic
    @Column(name = "username", unique = true, nullable = false)
    @Size(min = 4, max = 16) @NotNull
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password_hash", nullable = false)
    @Size(min = 8, max = 255) @NotNull
    @JsonIgnore
    public String getPassword() {
        return passwordHash;
    }

    public void setPassword(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    public Role getRole()
    {
        return role;
    }
    public void setRole(Role role)
    {
        this.role = role;
    }
    public void setRawPassword(String password) {
        this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
    }

}

package ba.unsa.etf.bp.udat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * Created by Edin on 11.11.2017..
 */
@Entity
@Table(name="roles")
public class Role extends BaseModel {
    private String name;
    private Collection<User> users;

    public Role() {}

    @Basic
    @Column(name = "name", nullable = false)
    @Size(max = 50) @NotNull
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    public Collection<User> getUsers()
    {
        return  users;
    }
    public void setUsers(Collection<User> users)
    {
        this.users = users;
    }

}

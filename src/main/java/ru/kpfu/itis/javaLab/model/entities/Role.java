package ru.kpfu.itis.javaLab.model.entities;

import ru.kpfu.itis.javaLab.model.enums.UserRole;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Safin Ramil on 07.06.17
 * Safin.Ramil@ordotrans.ru
 */

@Entity
@Table(
    name = "roles",
    uniqueConstraints = @UniqueConstraint(name = "roles_unique_key_name", columnNames = "name")
)
public class Role implements Serializable {

    private Long id;

    private UserRole name;

    private Set<User> users = new LinkedHashSet<>(0);

    public Role() {
    }

    public Role(UserRole role) {
        this.name = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Enumerated(EnumType.STRING)
    public UserRole getName() {
        return name;
    }

    @ManyToMany(mappedBy = "roles")
    public Set<User> getUsers() {
        return users;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(UserRole name) {
        this.name = name;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return name == role.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", users=").append(users);
        sb.append('}');
        return sb.toString();
    }
}

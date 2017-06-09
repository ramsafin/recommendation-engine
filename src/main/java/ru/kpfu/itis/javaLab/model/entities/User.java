package ru.kpfu.itis.javaLab.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Safin Ramil on 07.06.17
 * Safin.Ramil@ordotrans.ru
 */

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "users_unique_key_email", columnNames = "email"))

@NamedEntityGraphs(value = {

    @NamedEntityGraph(
        name = "graph.users.roles",
        attributeNodes = {
            @NamedAttributeNode(value = "roles")
        }
    )
})
public class User implements Serializable {

    private Long id;

    private String email;

    private String password;

    private String name;

    private String surname;

    private LocalDateTime registered;

    private String picture;


    // relations

    private Set<Role> roles = new LinkedHashSet<>(0);


    public User() {

    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(length = 128)
    public String getEmail() {
        return email;
    }

    @Column(nullable = false, length = 128)
    public String getPassword() {
        return password;
    }

    @Column(length = 128)
    public String getName() {
        return name;
    }

    @Column(length = 128)
    public String getSurname() {
        return surname;
    }

    @Column(nullable = false, updatable = false)
    public LocalDateTime getRegistered() {
        return registered;
    }

    @Column(length = 256)
    public String getPicture() {
        return picture;
    }

    // relations

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"),
        foreignKey = @ForeignKey(name = "fk_users_roles_users_id"),
        inverseForeignKey = @ForeignKey(name = "fk_users_roles_roles_id")
    )
    public Set<Role> getRoles() {
        return roles;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    // util method
    public void addRole(Role role) {
        role.getUsers().add(this);
        this.getRoles().add(role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(password, user.password) &&
            Objects.equals(email, user.email) &&
            Objects.equals(name, user.name) &&
            Objects.equals(surname, user.surname) &&
            Objects.equals(picture, user.picture) &&
            Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, email, name, surname, picture, roles);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", registered=").append(registered);
        sb.append(", picture='").append(picture).append('\'');
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}

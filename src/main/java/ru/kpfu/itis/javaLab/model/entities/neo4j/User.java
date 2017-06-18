package ru.kpfu.itis.javaLab.model.entities.neo4j;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Safin Ramil on 18.06.17
 * RamilSafNab1996@gmail.com
 */

@NodeEntity(label = "User")
public class User implements Serializable {

    private Long id;

    private Set<Post> posts = new HashSet<>(0);

    public User() {

    }

    public User(Long id) {
        this.id = id;
    }

    @GraphId
    @Property(name = "id")
    public Long getId() {
        return id;
    }

    @Relationship(type = "HAS_LIKED")
    public Set<Post> getPosts() {
        return posts;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

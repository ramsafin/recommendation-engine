package ru.kpfu.itis.javaLab.model.entities.neo4j;

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
@NodeEntity(label = "Post")
public class Post implements Serializable {

    private Long id;

    private Set<Tag> tags = new HashSet<>(0);

    public Post() {

    }

    @Property(name = "id")
    public Long getId() {
        return id;
    }

    @Relationship(type = "HAS")
    public Set<Tag> getTags() {
        return tags;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

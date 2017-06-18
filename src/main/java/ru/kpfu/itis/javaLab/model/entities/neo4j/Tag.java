package ru.kpfu.itis.javaLab.model.entities.neo4j;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Safin Ramil on 18.06.17
 * RamilSafNab1996@gmail.com
 */

@NodeEntity(label = "Tag")
public class Tag implements Serializable {

    private Long id;

    private String name;

    public Tag() {

    }

    @GraphId
    @Property(name = "id")
    public Long getId() {
        return id;
    }

    @Property(name = "name")
    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package ru.kpfu.itis.javaLab.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Safin Ramil on 09.06.17
 * RamilSafNab1996@gmail.com
 */

@Entity
@Table(name = "tags", uniqueConstraints = @UniqueConstraint(name = "tags_unique_key_name", columnNames = "name"))
public class Tag implements Serializable {

    private Long id;

    private String name;

    public Tag() {

    }

    public Tag(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

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
        return Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tag{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

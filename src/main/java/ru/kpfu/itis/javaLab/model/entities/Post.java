package ru.kpfu.itis.javaLab.model.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Safin Ramil on 09.06.17
 * RamilSafNab1996@gmail.com
 */

@Entity
@Table(name = "posts")

@NamedEntityGraphs(value = {

    @NamedEntityGraph(
        name = "graphs.posts.author",
        attributeNodes = {
            @NamedAttributeNode(value = "author")
        }
    )
})
public class Post implements Serializable {

    private Long id;

    private String title;

    private String content;

    private String picture;

    private LocalDateTime created;

    private LocalDateTime updated;

    private User author;
    private Long authorId;


    private Set<Tag> tags = new HashSet<>(0);

    public Post() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(length = 256, nullable = false)
    public String getTitle() {
        return title;
    }

    @Column(length = 10000, nullable = false)
    public String getContent() {
        return content;
    }

    @Column(length = 128)
    public String getPicture() {
        return picture;
    }

    @Column(nullable = false, updatable = false)
    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false, insertable = false, updatable = false)
    public User getAuthor() {
        return author;
    }

    @Column(name = "author_id", nullable = false)
    public Long getAuthorId() {
        return authorId;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "posts_tags",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id"),
        foreignKey = @ForeignKey(name = "fk_posts_tags_posts_id"),
        inverseForeignKey = @ForeignKey(name = "fk_posts_tags_tags_id")
    )
    public Set<Tag> getTags() {
        return tags;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(title, post.title) &&
            Objects.equals(content, post.content) &&
            Objects.equals(picture, post.picture) &&
            Objects.equals(tags, post.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, picture, tags);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Post{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", picture='").append(picture).append('\'');
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append(", author=").append(author);
        sb.append(", authorId=").append(authorId);
        sb.append(", tags=").append(tags);
        sb.append('}');
        return sb.toString();
    }
}

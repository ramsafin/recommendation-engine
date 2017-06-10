package ru.kpfu.itis.javaLab.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created by Safin Ramil on 09.06.17
 * RamilSafNab1996@gmail.com
 */

@Entity
@Table(
    name = "stars",
    uniqueConstraints = @UniqueConstraint(name = "stars_unique_key_user_post", columnNames = {"user_id", "post_id"})
)
public class Star implements Serializable {

    private Long id;

    private LocalDateTime created;

    private User user;
    private Long userId;

    private Post post;
    private Long postId;

    public Star() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(updatable = false, nullable = false)
    public LocalDateTime getCreated() {
        return created;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", updatable = false, insertable = false, nullable = false)
    public User getUser() {
        return user;
    }

    @Column(name = "user_id", nullable = false)
    public Long getUserId() {
        return userId;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", updatable = false, insertable = false, nullable = false)
    public Post getPost() {
        return post;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Star star = (Star) o;
        return Objects.equals(userId, star.userId) &&
            Objects.equals(postId, star.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, postId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Star{");
        sb.append("id=").append(id);
        sb.append(", created=").append(created);
        sb.append(", user=").append(user);
        sb.append(", userId=").append(userId);
        sb.append(", post=").append(post);
        sb.append(", postId=").append(postId);
        sb.append('}');
        return sb.toString();
    }
}

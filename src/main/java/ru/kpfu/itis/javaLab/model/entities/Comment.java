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
@Table(name = "comments")
public class Comment implements Serializable {

    private Long id;

    private String content;

    private LocalDateTime created;

    private User commenter;
    private Long commenterId;

    private Post post;
    private Long postId;

    private String commentorName;

    public Comment() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(length = 5000, nullable = false)
    public String getContent() {
        return content;
    }

    @Column(updatable = false, nullable = false)
    public LocalDateTime getCreated() {
        return created;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "commenter_id", insertable = false, updatable = false, nullable = false)
    public User getCommenter() {
        return commenter;
    }

    @Column(name = "commenter_id", nullable = false)
    public Long getCommenterId() {
        return commenterId;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", insertable = false, updatable = false, nullable = false)
    public Post getPost() {
        return post;
    }

    @Column(name = "post_id", nullable = false)
    public Long getPostId() {
        return postId;
    }

    @Column(name = "commentor_name")
    public String getCommentorName() {
        return commentorName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }

    public void setCommenterId(Long commenterId) {
        this.commenterId = commenterId;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public void setCommentorName(String commentorName) {
        this.commentorName = commentorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(content, comment.content) &&
            Objects.equals(commenterId, comment.commenterId) &&
            Objects.equals(postId, comment.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, commenterId, postId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("id=").append(id);
        sb.append(", content='").append(content).append('\'');
        sb.append(", created=").append(created);
        sb.append(", commenterId=").append(commenterId);
        sb.append(", postId=").append(postId);
        sb.append('}');
        return sb.toString();
    }
}

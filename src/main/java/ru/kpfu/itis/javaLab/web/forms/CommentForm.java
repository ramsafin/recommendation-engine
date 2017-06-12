package ru.kpfu.itis.javaLab.web.forms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Safin Ramil on 11.06.17
 * RamilSafNab1996@gmail.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentForm implements Serializable {

    private String content;

    private Long postId;

    public CommentForm() {

    }

    @NotEmpty
    @Length(min = 5, max = 1000)
    @JsonProperty(value = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @NotNull
    @JsonProperty(value = "postId")
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CommentForm{");
        sb.append("content='").append(content).append('\'');
        sb.append(", postId=").append(postId);
        sb.append('}');
        return sb.toString();
    }
}

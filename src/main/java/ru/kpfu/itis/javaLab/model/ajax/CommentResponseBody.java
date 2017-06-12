package ru.kpfu.itis.javaLab.model.ajax;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * Created by Safin Ramil on 11.06.17
 * RamilSafNab1996@gmail.com
 */

public class CommentResponseBody {

    private String commenterName;

    private LocalDateTime created;

    private String content;

    public CommentResponseBody() {

    }

    public CommentResponseBody(String commenterName, LocalDateTime created, String content) {
        this.commenterName = commenterName;
        this.created = created;
        this.content = content;
    }

    @JsonProperty("commenterName")
    public String getCommenterName() {
        return commenterName;
    }

    @JsonProperty("created")
    public LocalDateTime getCreated() {
        return created;
    }

    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package ru.kpfu.itis.javaLab.web.forms;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.javaLab.web.validators.annotations.ImageValid;

/**
 * Created by Safin Ramil on 12.06.17
 * RamilSafNab1996@gmail.com
 */

@ImageValid(message = "Invalid image type, support image/* (jpg, jpeg, png)")
public class PostForm {

    private String title;

    private String content;

    private MultipartFile picture;

    public PostForm() {

    }

    @Length(min = 3, max = 256)
    public String getTitle() {
        return title;
    }

    @Length(min = 3, max = 10000)
    public String getContent() {
        return content;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PostForm{");
        sb.append("title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", picture=").append(picture);
        sb.append('}');
        return sb.toString();
    }
}

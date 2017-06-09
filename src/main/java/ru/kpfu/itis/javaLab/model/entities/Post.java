package ru.kpfu.itis.javaLab.model.entities;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Safin Ramil on 09.06.17
 * RamilSafNab1996@gmail.com
 */

@Entity
@Table(name = "posts")
public class Post implements Serializable {

    private Long id;

    private String title;

    private String content;

    private String picture;

    private LocalDateTime created;

    private LocalDateTime updated;


    private User author;

    private Set<Tag> tags = new HashSet<>(0);

    private Set<Comment> comments = new LinkedHashSet<>(0);

    private Set<Star> starts = new HashSet<>(0);

}

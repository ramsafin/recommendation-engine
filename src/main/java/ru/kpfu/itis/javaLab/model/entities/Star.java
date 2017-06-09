package ru.kpfu.itis.javaLab.model.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Safin Ramil on 09.06.17
 * RamilSafNab1996@gmail.com
 */

@Entity
@Table(name = "stars")
public class Star implements Serializable {

    private Long id;

    private LocalDateTime created;

    private User user; // by user

    private Post post; // to post


}

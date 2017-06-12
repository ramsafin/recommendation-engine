package ru.kpfu.itis.javaLab.service.interfaces;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.javaLab.model.entities.Post;
import ru.kpfu.itis.javaLab.model.entities.User;
import ru.kpfu.itis.javaLab.web.forms.PostForm;

/**
 * Created by Safin Ramil on 12.06.17
 * RamilSafNab1996@gmail.com
 */

@Service
public interface AdminService {

    Post addPost(PostForm postForm, User author);
}

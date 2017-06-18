package ru.kpfu.itis.javaLab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import ru.kpfu.itis.javaLab.repository.spring.neo4j.Neo4jPostRepository;

@SpringBootApplication
@EntityScan({ "ru.kpfu.itis.javaLab.model.entities", "BOOT-INF.classes.ru.kpfu.itis.javaLab.entities" })
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Autowired
    private Neo4jPostRepository repository;

}

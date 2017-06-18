package ru.kpfu.itis.javaLab.repository.spring.neo4j;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.javaLab.model.entities.neo4j.Post;

import java.util.List;

public interface Neo4jPostRepository extends GraphRepository<Post> {

    @Query("MATCH (:User {id: {userId}})-[:HAS_LIKED]->(likedPost:Post) " +
        "WITH collect(DISTINCT likedPost.id) AS likedPosts " +
        "MATCH (user:User)-[like:HAS_LIKED]->(post:Post)-[:HAS]->(tag:Tag) " +
        "WHERE NOT (post.id IN likedPosts) " +
        "WITH post.id AS postID, count(DISTINCT like) AS likes " +
        "ORDER BY likes DESC " +
        "RETURN postID " +
        "LIMIT 5;")
    List<Long> findRecommended(@Param("userId") Long userId);
}

package ru.kpfu.itis.javaLab.repository.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.javaLab.model.entities.Tag;

import java.util.List;
import java.util.Optional;

/**
 * Created by Safin Ramil on 10.06.17
 * RamilSafNab1996@gmail.com
 * <p>
 * Tag entity repository
 */

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    /**
     * Find all tags by the list of their names
     *
     * @param names - list of tag names
     * @return tags with the names containing in the list
     */
    @Query("select t from Tag t where t.name in :names")
    List<Tag> findByNamesIn(@Param("names") List<String> names);


    /**
     * Find all tags by the list of their ids
     *
     * @param ids - list of tag ids
     * @return tags with the ids containing in the list
     */
    @Query("select t from Tag t where t.id in :ids")
    List<Tag> findByIdsIn(@Param("ids") List<Long> ids);


    /**
     * Find tag by its name
     *
     * @param name - tag name
     * @return tag or null
     */
    Optional<Tag> findByName(@Param("name") String name);
}

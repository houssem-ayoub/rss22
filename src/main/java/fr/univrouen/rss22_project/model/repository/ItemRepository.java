package fr.univrouen.rss22_project.model.repository;

import fr.univrouen.rss22_project.model.orm.FeedORM;
import fr.univrouen.rss22_project.model.orm.ItemORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ItemRepository  extends JpaRepository<ItemORM, String> {

    @Query(value = "SELECT i FROM items i WHERE i.feed= :feed")
    Set<ItemORM> findAllByFeed(@Param("feed") FeedORM feedORM);
}

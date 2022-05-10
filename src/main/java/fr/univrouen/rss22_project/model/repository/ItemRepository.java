package fr.univrouen.rss22_project.model.repository;

import fr.univrouen.rss22_project.model.orm.FeedORM;
import fr.univrouen.rss22_project.model.orm.ItemORM;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository  extends JpaRepository<ItemORM, String> , JpaSpecificationExecutor<ItemORM> {

    @Query(value = "SELECT i FROM items i WHERE i.feed= :feed")
    List<ItemORM> findAllByFeed(@Param("feed") FeedORM feedORM);
}

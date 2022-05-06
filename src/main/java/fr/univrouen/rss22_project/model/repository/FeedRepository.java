package fr.univrouen.rss22_project.model.repository;

import fr.univrouen.rss22_project.model.orm.FeedORM;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepository extends JpaRepository<FeedORM,Long> {
    @Query(value = "SELECT f FROM feeds f WHERE f.title = ?1 and f.pubDate=?2")
    FeedORM findFeedByTitleAndPubDate(String title, DateTime pubDate);
}

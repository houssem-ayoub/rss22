package fr.univrouen.rss22_project.repository;

import fr.univrouen.rss22_project.model.orm.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepository extends JpaRepository<Feed,Long> {
}

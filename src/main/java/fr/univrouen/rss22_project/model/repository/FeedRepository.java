package fr.univrouen.rss22_project.model.repository;

import fr.univrouen.rss22_project.model.orm.FeedORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepository extends JpaRepository<FeedORM,Long> {
}

package fr.univrouen.rss22_project.repository;

import fr.univrouen.rss22_project.model.orm.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository  extends JpaRepository<Item, String> {

}

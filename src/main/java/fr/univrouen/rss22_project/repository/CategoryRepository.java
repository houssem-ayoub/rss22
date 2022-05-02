package fr.univrouen.rss22_project.repository;

import fr.univrouen.rss22_project.model.orm.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT c FROM categories  c WHERE c.term IN :terms")
    Set<Category> findAllByTermList(@Param("terms") List<String> terms);
}

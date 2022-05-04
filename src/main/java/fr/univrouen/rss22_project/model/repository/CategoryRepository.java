package fr.univrouen.rss22_project.model.repository;

import fr.univrouen.rss22_project.model.orm.CategoryORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryORM, Long> {
    /*@Query(value = "SELECT c FROM categories  c WHERE c.term IN :terms")
    Set<CategoryORM> findAllByTermList(@Param("terms") List<String> terms);*/
    @Query(value = "SELECT c FROM categories  c WHERE c.term = ?1")
    CategoryORM findByTerm(String term);
}

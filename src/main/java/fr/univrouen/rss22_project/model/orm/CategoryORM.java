package fr.univrouen.rss22_project.model.orm;

import javax.persistence.*;

@Entity(name = "categories")
@Table(name = "categories")
public class CategoryORM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;
    @Column(nullable = false,unique = true)
    private String term = null;


    public CategoryORM() {
    }

    public CategoryORM(String term) {
        this.term = term;
    }
    public Long getId() {
        return id;
    }

    public String getTerm() {
        return term;
    }
}

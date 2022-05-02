package fr.univrouen.rss22_project.model.orm;

import javax.persistence.*;

@Entity(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String term = null;


    public Category() {
    }

    public Category(String term) {
        this.term = term;
    }
    public Long getId() {
        return id;
    }

    public String getTerm() {
        return term;
    }

    fr.univrouen.rss22_project.model.feed.Category toXmlObject(){
        return new fr.univrouen.rss22_project.model.feed.Category(term);
    }
}

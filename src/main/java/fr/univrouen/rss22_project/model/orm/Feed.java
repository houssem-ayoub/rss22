package fr.univrouen.rss22_project.model.orm;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "feeds")
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String pubDate;

    public Feed() {
    }

    public Feed(String title, String pubDate) {
        this.title = title;
        this.pubDate = pubDate;
    }
    //    private List
}

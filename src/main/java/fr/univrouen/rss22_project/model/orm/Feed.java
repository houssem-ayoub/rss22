package fr.univrouen.rss22_project.model.orm;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
@Entity
@Table(name = "feeds")
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "DATETIME(3)")
    private DateTime pubDate;

    public Feed() {
    }

    public Feed(String title, DateTime pubDate) {
        this.title = title;
        this.pubDate = pubDate;
    }
    //    private List
}

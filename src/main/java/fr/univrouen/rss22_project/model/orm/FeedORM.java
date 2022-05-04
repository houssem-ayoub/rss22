package fr.univrouen.rss22_project.model.orm;

import org.joda.time.DateTime;

import javax.persistence.*;
@Entity(name = "feeds")
@Table(name = "feeds")
public class FeedORM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private Long id;
    private String title;
    @Column(columnDefinition = "DATETIME(3)")
    private DateTime pubDate;

    public FeedORM() {
    }

    public FeedORM(String title, DateTime pubDate) {
        this.title = title;
        this.pubDate = pubDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public DateTime getPubDate() {
        return pubDate;
    }
}

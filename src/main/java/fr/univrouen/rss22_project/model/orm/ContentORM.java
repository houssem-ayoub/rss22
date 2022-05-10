package fr.univrouen.rss22_project.model.orm;

import javax.persistence.*;

@Entity(name = "contents")
@Table(name = "contents")
public class ContentORM {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "content_id")
    private final Long id = null;
    private boolean isLink;
    @Lob
    @Column(nullable = false,columnDefinition = "TEXT")
    private String text;

    public ContentORM() {
    }

    public ContentORM(boolean isLink, String text) {
        this.isLink = isLink;
        this.text = text;
    }

    public boolean isLink() {
        return isLink;
    }

    public String getText() {
        return text;
    }
}

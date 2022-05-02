package fr.univrouen.rss22_project.model.orm;

import fr.univrouen.rss22_project.model.feed.ContentType;

import javax.persistence.*;

@Entity
@Table(name = "contents")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id = null;
    private boolean isLink;
    @Column(nullable = false)
    private String text;

    public Content() {
    }

    public Content(boolean isLink, String text) {
        this.isLink = isLink;
        this.text = text;
    }

    fr.univrouen.rss22_project.model.feed.Content toXmlObject(){
        if(isLink){
            return new fr.univrouen.rss22_project.model.feed.Content(ContentType.SRC,text);
        }
        return new fr.univrouen.rss22_project.model.feed.Content(ContentType.TEXT,text);
    }
}

package fr.univrouen.rss22_project.model.orm;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private String href;
    private String alt;
    private String length = null;

    public Image() {
    }

    public Image(String type, String href, String alt, String length) {
        this.type = type;
        this.href = href;
        this.alt = alt;
        this.length = length;
    }

    fr.univrouen.rss22_project.model.feed.Image toXMLObject(){
        return new fr.univrouen.rss22_project.model.feed.Image(type,href,alt,length);
    }
}

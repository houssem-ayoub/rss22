package fr.univrouen.rss22_project.model.orm;

import javax.persistence.*;

@Entity(name = "images")
@Table(name = "images")
public class ImageORM {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id")
    private Long id;
    private String type;
    private String href;
    private String alt;
    private String length = null;

    public ImageORM() {
    }

    public ImageORM(String type, String href, String alt, String length) {
        this.type = type;
        this.href = href;
        this.alt = alt;
        this.length = length;
    }

    public String getType() {
        return type;
    }

    public String getHref() {
        return href;
    }

    public String getAlt() {
        return alt;
    }

    public String getLength() {
        return length;
    }
}

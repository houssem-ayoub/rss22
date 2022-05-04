package fr.univrouen.rss22_project.model.orm;


import fr.univrouen.rss22_project.model.xml.LinkRelXML;
import fr.univrouen.rss22_project.model.xml.LinkXML;

import javax.persistence.*;

@Entity(name = "links")
@Table(name = "links")
public class LinkORM {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "link_id")
    private Long id;
    @Column(nullable = false)
    private String rel;
    private String type;
    private String href;

    public LinkORM() {
    }

    public LinkORM(String rel, String type, String href) {
        this.rel = rel;
        this.type = type;
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public String getType() {
        return type;
    }

    public String getHref() {
        return href;
    }
}

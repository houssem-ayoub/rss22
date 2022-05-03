package fr.univrouen.rss22_project.model.orm;


import fr.univrouen.rss22_project.model.xml.LinkRel;

import javax.persistence.*;

@Entity
@Table(name = "links")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String rel;
    private String type;
    private String href;

    public Link() {
    }

    public Link(String rel, String type, String href) {
        this.rel = rel;
        this.type = type;
        this.href = href;
    }

    fr.univrouen.rss22_project.model.xml.Link toXMLObject(){
        return new fr.univrouen.rss22_project.model.xml.Link(
                rel.equals("SELF")? LinkRel.SELF:LinkRel.ALTERNATE,
                type, href);
    }
}

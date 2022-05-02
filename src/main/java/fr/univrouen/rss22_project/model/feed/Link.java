package fr.univrouen.rss22_project.model.feed;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "link")
public class Link {

    @XmlAttribute(required = true)
    private LinkRel rel;
    @XmlAttribute(required = true)
    private String type;
    @XmlAttribute(required = true)
    private String href;

    public Link() {
    }

    public Link(LinkRel rel, String type, String href) {
        this.rel = rel;
        this.type = type;
        this.href = href;
    }

    public LinkRel getRel() {
        return rel;
    }

    public String getType() {
        return type;
    }

    public String getHref() {
        return href;
    }
}

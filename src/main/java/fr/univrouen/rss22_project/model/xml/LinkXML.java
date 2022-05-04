package fr.univrouen.rss22_project.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "link")
public class LinkXML {
    @XmlAttribute(required = true)
    private LinkRelXML rel;
    @XmlAttribute(required = true)
    private String type;
    @XmlAttribute(required = true)
    private String href;

    public LinkXML() {
    }

    public LinkXML(LinkRelXML rel, String type, String href) {
        this.rel = rel;
        this.type = type;
        this.href = href;
    }

    public LinkRelXML getRel() {
        return rel;
    }

    public String getType() {
        return type;
    }

    public String getHref() {
        return href;
    }
}

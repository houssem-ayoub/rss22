package fr.univrouen.rss22_project.model.feed;

import javax.xml.bind.annotation.XmlAttribute;

public class Image {
    @XmlAttribute(required = true)
    private String type;
    @XmlAttribute(required = true)
    private String href;
    @XmlAttribute(required = true)
    private String alt;
    @XmlAttribute
    private String length = null;

    public Image() {
    }
    public Image(String type, String href, String alt) {
        this.type = type;
        this.href = href;
        this.alt = alt;
    }
    public Image(String type, String href, String alt, String length) {
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

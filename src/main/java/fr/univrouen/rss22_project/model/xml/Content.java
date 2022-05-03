package fr.univrouen.rss22_project.model.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
public class Content {

    @XmlAttribute(required = true)
    private ContentType type;
    @XmlAttribute
    private String href = null;
    @XmlValue
    private String content = null;

    public Content() {
    }

    public Content(ContentType type, String data) {
        this.type = type;
        if(type == ContentType.TEXT)
            content = data;
        else
            href = data;
    }

    public ContentType getType() {
        return type;
    }

    public String getHref() {
        return href;
    }

    public String getContent() {
        return content;
    }
}
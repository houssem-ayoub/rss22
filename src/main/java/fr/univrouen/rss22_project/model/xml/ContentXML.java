package fr.univrouen.rss22_project.model.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
public class ContentXML {
    @XmlAttribute(required = true)
    private ContentTypeXML type;
    @XmlAttribute
    private String href = null;
    @XmlValue
    private String content = null;
    public ContentXML() {
    }

    public ContentXML(ContentTypeXML type, String data) {
        this.type = type;
        if(type == ContentTypeXML.TEXT)
            content = data;
        else
            href = data;
    }

    public ContentTypeXML getType() {
        return type;
    }

    public String getHref() {
        return href;
    }

    public String getContent() {
        return content;
    }
}

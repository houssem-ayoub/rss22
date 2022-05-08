package fr.univrouen.rss22_project.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "response", namespace = "http://univrouen.fr/rss22")
public class ResponseXML {
    @XmlElement
    private String guid;
    @XmlElement
    private String status;
    @XmlElement
    private String description;

    public ResponseXML() {}

    public ResponseXML(String guid, String status, String description) {
        this.guid = guid;
        this.status = status;
        this.description = description;
    }

    public String getGuid() {
        return guid;
    }

    public String getDescription() {
        return description;
    }
}

package fr.univrouen.rss22_project.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "author")
@XmlAccessorType(XmlAccessType.NONE)
public class AuthorXML extends PersonXML {
    public AuthorXML() {
    }
    public AuthorXML(String name, String email, LinkXML uri) {
        super(name, email, uri);
    }
}

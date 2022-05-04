package fr.univrouen.rss22_project.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contributor")
@XmlAccessorType(XmlAccessType.NONE)
public class ContributorXML extends PersonXML {
    public ContributorXML() {
    }

    public ContributorXML(String name, String email, LinkXML uri) {
        super(name, email, uri);
    }
}

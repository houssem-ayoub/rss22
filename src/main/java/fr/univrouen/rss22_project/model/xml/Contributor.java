package fr.univrouen.rss22_project.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contributor")
@XmlAccessorType(XmlAccessType.NONE)
public class Contributor extends Person{
    public Contributor() {
    }

    public Contributor(String name) {
        super(name);
    }

    public Contributor(String name, String email, Link uri) {
        super(name, email, uri);
    }
}

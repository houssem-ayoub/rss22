package fr.univrouen.rss22_project.model.feed;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contributor")
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

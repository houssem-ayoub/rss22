package fr.univrouen.rss22_project.model.feed;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.NONE)
public abstract class Person {
    @XmlElement
    protected String name;
    @XmlElement
    protected String email= null;
    @XmlElement
    protected Link uri = null;

    public Person() {
    }
    public Person(String name) {
        this.name = name;
    }
    public Person(String name, String email, Link uri) {
        this.name = name;
        this.email = email;
        this.uri = uri;
    }
}

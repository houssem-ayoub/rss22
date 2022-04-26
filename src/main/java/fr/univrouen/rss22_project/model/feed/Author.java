package fr.univrouen.rss22_project.model.feed;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "author")
public class Author extends Person{
    public Author() {
    }

    public Author(String name) {
        super(name);
    }

    public Author(String name, String email, Link uri) {
        super(name, email, uri);
    }
}

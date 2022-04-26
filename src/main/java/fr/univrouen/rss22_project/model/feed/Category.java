package fr.univrouen.rss22_project.model.feed;

import javax.xml.bind.annotation.XmlAttribute;

public class Category {
    @XmlAttribute(required = true)
    private String term;

    public Category(String term) {
        this.term = term;
    }

    public Category() {
    }
}

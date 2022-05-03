package fr.univrouen.rss22_project.model.xml;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAttribute;
public class Category {

    @XmlAttribute(required = true)
    @Column(unique = true)
    private String term;

    public Category(String term) {
        this.term = term;
    }

    public Category() {
    }

    public String getTerm() {
        return term;
    }
}

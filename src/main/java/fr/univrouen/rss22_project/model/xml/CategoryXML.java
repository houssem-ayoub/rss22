package fr.univrouen.rss22_project.model.xml;

import javax.xml.bind.annotation.XmlAttribute;
public class CategoryXML {
    @XmlAttribute(required = true)
    private String term;
    public CategoryXML() {
    }
    public CategoryXML(String term) {
        this.term = term;
    }

    public String getTerm() {
        return term;
    }
}

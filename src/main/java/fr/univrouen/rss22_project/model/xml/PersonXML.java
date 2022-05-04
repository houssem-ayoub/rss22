package fr.univrouen.rss22_project.model.xml;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.NONE)
public abstract class PersonXML {

   @XmlElement
    protected String name;
   @XmlElement
    protected String email= null;
   @XmlElement
    protected LinkXML uri = null;

    public PersonXML() {
    }
    public PersonXML(String name, String email, LinkXML uri) {
        this.name = name;
        this.email = email;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LinkXML getUri() {
        return uri;
    }
}

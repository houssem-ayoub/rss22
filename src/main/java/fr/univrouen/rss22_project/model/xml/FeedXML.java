package fr.univrouen.rss22_project.model.xml;


import fr.univrouen.rss22_project.model.adapter.XMLDateTimeAdapter;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.HashSet;
import java.util.Set;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "feed", namespace = "http://univrouen.fr/rss22")
public class FeedXML {
    @XmlAttribute(required = true)
    private String lang;
    @XmlElement
    private String title;
    @XmlElement
    @XmlJavaTypeAdapter(XMLDateTimeAdapter.class)
    private DateTime pubDate;
    @XmlElement
    private String copyright;
    @XmlElement(name = "link")
    private final Set<LinkXML> links;
    @XmlElement(name = "item")
    private final Set<ItemXML> items;
    public FeedXML(){
        links = new HashSet<>();
        items = new HashSet<>(10);
    }

    public String getTitle(){
        return this.title;
    }

    public DateTime getPubDate() {
        return pubDate;
    }

    public Set<ItemXML> getItems() {
        return items;
    }
}

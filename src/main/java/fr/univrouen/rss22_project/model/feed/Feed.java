package fr.univrouen.rss22_project.model.feed;


import javax.xml.bind.annotation.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "feed", namespace = "http://univrouen.fr/rss22")
public class Feed {
    @XmlAttribute(required = true)
    private String lang;
    @XmlElement
    private String title;
    @XmlElement
    private String pubDate;
    @XmlElement
    private String copyright;
    @XmlElement(name = "link")
    private final Set<Link> links;
    @XmlElement(name = "item")
    private final Set<Item> items;
    public Feed(){
        links = new HashSet<>();
        items = new HashSet<>(10);
    }
    public Feed(String lang, String title, String pubDate, String copyright) {
        this();
        this.lang = lang;
        this.title = title;
        this.pubDate = pubDate;
        this.copyright = copyright;
    }
    public String getTitle(){
        return this.title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void addLinks(Collection<Link> links){
        this.links.addAll(links);
    }
    public void addItems(Collection<Item> items){
        this.items.addAll(items);
    }

    public Set<Item> getItems() {
        return items;
    }
}

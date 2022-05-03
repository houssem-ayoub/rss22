package fr.univrouen.rss22_project.model.xml;


import fr.univrouen.rss22_project.model.adapter.XMLDateTimeAdapter;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.*;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.NONE)
public class Item {
    @XmlAttribute
    private String guid = null;
    @XmlElement
    private String title;
    @XmlElement(name = "category")
    Set<Category> categories;
    @XmlElement
    @XmlJavaTypeAdapter(XMLDateTimeAdapter.class)
    private DateTime published = null;
    @XmlElement
    @XmlJavaTypeAdapter(XMLDateTimeAdapter.class)
    private DateTime updated = null;
    @XmlElement
    private Image image = null;
    @XmlElement
    private Content content = null;
   //@XmlAnyElement(lax = true)
   @XmlElementRefs({
           @XmlElementRef(name = "author",type = Author.class),
           @XmlElementRef(name = "contributor",type = Contributor.class),
   })
    private final Set<Person> personSet;

    public Item() {
        categories = new HashSet<>();
        personSet = new HashSet<>();
    }

    public Item(String guid, String title, DateTime date, boolean isPublished) {
        this();
        this.guid = guid;
        this.title = title;
        if(isPublished)
            this.published = date;
        else
            this.updated = date;
    }
    public Item(String guid, String title, DateTime date, boolean isPublished,Image image, Content content) {
        this();
        this.guid = guid;
        this.title = title;
        if(isPublished)
            this.published = date;
        else
            this.updated = date;
        this.image = image;
        this.content = content;
    }

    public String getGuid() {
        return guid;
    }

    public String getTitle() {
        return title;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public DateTime getPublished() {
        return published;
    }

    public DateTime getUpdated() {
        return updated;
    }

    public Image getImage() {
        return image;
    }

    public Content getContent() {
        return content;
    }

    public Set<Person> getPersonSet() {
        return personSet;
    }

    public void addCategories(Collection<Category> categories) {
        this.categories.addAll(categories);
    }

    public void addPerson(Collection<Person> people) {
        this.personSet.addAll(people);
    }

    @Override
    public String toString() {
        return ("Article : " + title + "\n(" + guid
                + ") Le = " + published);
    }
}

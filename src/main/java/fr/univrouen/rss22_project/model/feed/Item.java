package fr.univrouen.rss22_project.model.feed;


import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Author.class,Contributor.class})
public class Item {
    @XmlAttribute
    private String guid;
    @XmlElement
    private String title;
    @XmlElement(name = "category")
    ArrayList<Category> categories;
    @XmlElement
    private String published = null;
    @XmlElement
    private String updated = null;
    @XmlElement
    private Image image = null;
    @XmlElement
    private Content content = null;
    @XmlAnyElement(lax = true)
    protected List<Person> person;

    public Item() {
        categories = new ArrayList<>();
        person = new ArrayList<>();
    }

    public Item(String guid, String title, String published) {
        this();
        this.guid = guid;
        this.title = title;
        this.published = published;
    }
    public Item(String guid, String title, String published,Image image, Content content) {
        this();
        this.guid = guid;
        this.title = title;
        this.published = published;
        this.image = image;
        this.content = content;
    }
    public Item(String guid, String title, String published,Image image, Content content, boolean isPublished) {
        this();
        this.guid = guid;
        this.title = title;
        this.image = image;
        this.content = content;
        if (isPublished) {
            this.published = published;
        } else {
            this.updated = published;
        }

    }

    public void addCategories(Collection<Category> categories) {
        this.categories.addAll(categories);
    }

    public void addPerson(Collection<Person> people) {
        this.person.addAll(people);
    }

    @Override
    public String toString() {
        return ("Article : " + title + "\n(" + guid
                + ") Le = " + published);
    }
}

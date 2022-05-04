package fr.univrouen.rss22_project.model.xml;


import fr.univrouen.rss22_project.model.adapter.XMLDateTimeAdapter;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.*;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.NONE)
public class ItemXML {
    @XmlAttribute
    private String guid = null;
    @XmlElement
    private String title;
    @XmlElement(name = "category")
    Set<CategoryXML> categories;
    @XmlElement
    @XmlJavaTypeAdapter(XMLDateTimeAdapter.class)
    private DateTime published = null;
    @XmlElement
    @XmlJavaTypeAdapter(XMLDateTimeAdapter.class)
    private DateTime updated = null;
    @XmlElement
    private ImageXML image = null;
    @XmlElement
    private ContentXML content = null;
   //@XmlAnyElement(lax = true)
   @XmlElementRefs({
           @XmlElementRef(name = "author",type = AuthorXML.class),
           @XmlElementRef(name = "contributor",type = ContributorXML.class),
   })
    private Set<PersonXML> personSet;

    public ItemXML() {
        categories = new HashSet<>();
        personSet = new HashSet<>();
    }

    public ItemXML(String guid, String title, DateTime date, boolean isPublished, ImageXML image, ContentXML content) {
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

    public Set<CategoryXML> getCategories() {
        return categories;
    }

    public DateTime getPublished() {
        return published;
    }

    public DateTime getUpdated() {
        return updated;
    }

    public ImageXML getImage() {
        return image;
    }

    public ContentXML getContent() {
        return content;
    }

    public Set<PersonXML> getPersonSet() {
        return personSet;
    }

    public void setCategories(Collection<CategoryXML> categories) {
        this.categories = Set.copyOf(categories);

    }

    public void setPersonCollection(Collection<PersonXML> people) {
        this.personSet = Set.copyOf(people);
    }
}

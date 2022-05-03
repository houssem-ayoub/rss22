package fr.univrouen.rss22_project.model.resume;

import fr.univrouen.rss22_project.model.adapter.XMLDateTimeAdapter;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "item")
public class ItemResume {
    @XmlAttribute
    private String guid;
    @XmlElement
    private String title;
    @XmlElement
    @XmlJavaTypeAdapter(XMLDateTimeAdapter.class)
    private DateTime date;

    public ItemResume() {
    }

    public ItemResume(String guid, String title, DateTime date) {
        this.guid = guid;
        this.title = title;
        this.date = date;
    }

    public String getGuid() {
        return guid;
    }

    public String getTitle() {
        return title;
    }

    public DateTime getDate() {
        return date;
    }
}

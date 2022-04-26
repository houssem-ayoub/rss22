package fr.univrouen.rss22_project.model.resume;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "item")
public class ItemResume {
    @XmlAttribute
    private String guid;
    @XmlElement
    private String title;
    @XmlElement
    private String date;

    public ItemResume() {
    }

    public ItemResume(String guid, String title, String date) {
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

    public String getDate() {
        return date;
    }
}

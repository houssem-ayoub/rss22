package fr.univrouen.rss22_project.model.xml.resume;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "items")
public class ItemXMLResumeList {
    @XmlElement(name = "item")
    private final Set<ItemXMLResume> resumeList;

    public ItemXMLResumeList() {
        this.resumeList = new HashSet<>();
    }

    public ItemXMLResumeList(Collection<ItemXMLResume> itemXMLResumeCollection) {
        this.resumeList = Set.copyOf(itemXMLResumeCollection);
    }

    public Collection<ItemXMLResume> getResumeList() {
        return resumeList;
    }
}

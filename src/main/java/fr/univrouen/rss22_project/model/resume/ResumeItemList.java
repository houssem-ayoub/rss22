package fr.univrouen.rss22_project.model.resume;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "items")
public class ResumeItemList {
    @XmlElement(name = "item")
    private final List<ItemResume> resumeList;

    public ResumeItemList() {
        this.resumeList = new ArrayList<>();
    }

    public ResumeItemList(List<ItemResume> resumeList) {
        this.resumeList = resumeList;
    }
}

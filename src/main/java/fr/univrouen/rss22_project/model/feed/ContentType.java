package fr.univrouen.rss22_project.model.feed;

import javax.xml.bind.annotation.XmlEnumValue;

public enum ContentType {
    @XmlEnumValue("text")
    TEXT,
    @XmlEnumValue("src")
    SRC
}

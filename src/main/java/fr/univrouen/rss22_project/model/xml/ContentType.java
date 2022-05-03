package fr.univrouen.rss22_project.model.xml;

import javax.xml.bind.annotation.XmlEnumValue;

public enum ContentType {
    @XmlEnumValue("text")
    TEXT,
    @XmlEnumValue("src")
    SRC
}

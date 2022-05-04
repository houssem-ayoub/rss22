package fr.univrouen.rss22_project.model.xml;


import javax.xml.bind.annotation.XmlEnumValue;

public enum LinkRelXML {
    @XmlEnumValue("self")
    SELF,
    @XmlEnumValue("alternate")
    ALTERNATE
}

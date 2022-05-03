package fr.univrouen.rss22_project.model.xml;


import javax.xml.bind.annotation.XmlEnumValue;

public enum LinkRel{
    @XmlEnumValue("self")
    SELF,
    @XmlEnumValue("alternate")
    ALTERNATE
}

package fr.univrouen.rss22_project.model.adapter;

import fr.univrouen.rss22_project.model.orm.LinkORM;
import fr.univrouen.rss22_project.model.xml.LinkRelXML;
import fr.univrouen.rss22_project.model.xml.LinkXML;
import org.springframework.stereotype.Component;

@Component
public class LinkAdapter {
    public LinkORM adaptToORM(LinkXML linkXML){
        if (linkXML == null)
            return null;
        return new LinkORM(
                linkXML.getRel()== LinkRelXML.SELF?"SELF":"ALTERNATE",
                linkXML.getType(),
                linkXML.getHref()
        );
    }
    public LinkXML adaptToXML(LinkORM linkORM){
        if (linkORM == null)
            return null;
        return new LinkXML(
                linkORM.getRel().equals("SELF")? LinkRelXML.SELF: LinkRelXML.ALTERNATE,
                linkORM.getType(), linkORM.getHref());
    }
}

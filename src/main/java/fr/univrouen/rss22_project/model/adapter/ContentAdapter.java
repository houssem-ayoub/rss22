package fr.univrouen.rss22_project.model.adapter;

import fr.univrouen.rss22_project.model.orm.ContentORM;
import fr.univrouen.rss22_project.model.xml.ContentTypeXML;
import fr.univrouen.rss22_project.model.xml.ContentXML;
import org.springframework.stereotype.Component;

@Component
public class ContentAdapter {
    public ContentORM adaptToORM(ContentXML contentXML){
       return new ContentORM(
                contentXML.getType()== ContentTypeXML.SRC,
                contentXML.getType()== ContentTypeXML.SRC?contentXML.getHref():contentXML.getContent()
        );
    }

    public ContentXML adaptToXML(ContentORM contentORM){
        if(contentORM.isLink()){
            return new ContentXML(ContentTypeXML.SRC,contentORM.getText());
        }
        return new ContentXML(ContentTypeXML.TEXT,contentORM.getText());
    }
}

package fr.univrouen.rss22_project.model.adapter;

import fr.univrouen.rss22_project.model.orm.ImageORM;
import fr.univrouen.rss22_project.model.xml.ImageXML;
import org.springframework.stereotype.Component;

@Component
public class ImageAdapter {
    public ImageORM adaptToORM(ImageXML imageXML){
        return new ImageORM(
                imageXML.getType(),
                imageXML.getHref(),
                imageXML.getAlt(),
                imageXML.getLength());
    }

    public ImageXML adaptToXml(ImageORM imageORM){
        return new ImageXML(imageORM.getType(),imageORM.getHref(),imageORM.getAlt(),imageORM.getLength());
    }
}

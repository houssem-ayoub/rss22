package fr.univrouen.rss22_project.model.adapter;

import fr.univrouen.rss22_project.model.feed.ContentType;
import fr.univrouen.rss22_project.model.orm.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ItemAdapter {

    public static Item adaptToORM(fr.univrouen.rss22_project.model.feed.Item itemXML, Feed feedORM){
        return new Item(
                feedORM, itemXML.getTitle(),
                itemXML.getCategories().stream().map(CategoryAdapter::AdaptToORM).collect(Collectors.toSet()),
                itemXML.getPublished()!=null? itemXML.getPublished() : itemXML.getUpdated(), itemXML.getUpdated()!=null,
                new Image(
                        itemXML.getImage().getType(),
                        itemXML.getImage().getHref(),
                        itemXML.getImage().getAlt(),
                        itemXML.getImage().getLength()),
                new Content(
                        itemXML.getContent().getType()== ContentType.SRC,
                            itemXML.getContent().getType()==ContentType.SRC?itemXML.getContent().getHref():itemXML.getContent().getContent()
                        ),
                PersonAdapter.adaptAllToORM(itemXML.getPersonSet())
        );
    }
}

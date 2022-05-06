package fr.univrouen.rss22_project.model.adapter;

import fr.univrouen.rss22_project.model.orm.FeedORM;
import fr.univrouen.rss22_project.model.orm.ItemORM;
import fr.univrouen.rss22_project.model.xml.ItemXML;
import fr.univrouen.rss22_project.model.xml.ItemXMLResume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class ItemAdapter {
    @Autowired
    private CategoryAdapter categoryAdapter;
    @Autowired
    private ContentAdapter contentAdapter;
    @Autowired
    private ImageAdapter imageAdapter;
    @Autowired
    private PersonAdapter personAdapter;
    private ItemORM adaptToORM(ItemXML itemXML, FeedORM feedORM){
        ItemORM itemORM = new ItemORM(
                feedORM, itemXML.getTitle(),
                itemXML.getPublished()!=null?
                        itemXML.getPublished() :
                        itemXML.getUpdated(),
                itemXML.getUpdated()!=null,
                imageAdapter.adaptToORM(itemXML.getImage()),
                contentAdapter.adaptToORM(itemXML.getContent())
        );
        itemORM.setCategoryCollection(categoryAdapter.adaptAllToORM(itemXML.getCategories()));
        itemORM.setPersonCollection(
                personAdapter.adaptAllToORM(itemXML.getPersonSet())
        );
        return itemORM;
    }

    public Collection<ItemORM> adaptAllToORM(Collection<ItemXML> itemXMLCollection,FeedORM feedORM){
        return itemXMLCollection.stream()
                .map(itemXML -> this.adaptToORM(itemXML,feedORM))
                .collect(Collectors.toList());
    }

    public ItemXML adaptToXML(ItemORM itemORM){
        ItemXML item = new ItemXML(
                itemORM.getGuid(),
                itemORM.getTitle(),
                itemORM.getDate(),
                !itemORM.isUpdated(),
                imageAdapter.adaptToXml(itemORM.getImage()),
                contentAdapter.adaptToXML(itemORM.getContent())
        );
        item.setPersonCollection(personAdapter.adaptAllToXML(itemORM.getContributors()));
        item.setCategories(categoryAdapter.adaptAllToXML(itemORM.getCategories()));
        return item;
    }
    public Collection<ItemXMLResume> adaptAllToResumeXML(Collection<ItemORM> itemORMCollection){
        return itemORMCollection
                .stream().map(
                        itemORM ->
                                new ItemXMLResume(
                                        itemORM.getGuid(),
                                        itemORM.getTitle(),
                                        itemORM.getDate())
                ).collect(Collectors.toList());
    }
}

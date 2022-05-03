package fr.univrouen.rss22_project.model.adapter;

import fr.univrouen.rss22_project.model.orm.Feed;
import fr.univrouen.rss22_project.model.orm.Item;

import java.util.Set;
import java.util.stream.Collectors;

public class FeedAdapter {

    public static Feed adaptToORM(fr.univrouen.rss22_project.model.xml.Feed feedXML){
        return new Feed(feedXML.getTitle(), feedXML.getPubDate());
    }
    public static Set<Item> getORMItems(fr.univrouen.rss22_project.model.xml.Feed feedXML, Feed feedORM){
        return feedXML.getItems().stream().map(
                item -> ItemAdapter.adaptToORM(item,feedORM)
        ).collect(Collectors.toSet());
    }
}

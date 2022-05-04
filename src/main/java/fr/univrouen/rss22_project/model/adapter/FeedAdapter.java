package fr.univrouen.rss22_project.model.adapter;

import fr.univrouen.rss22_project.model.orm.FeedORM;
import fr.univrouen.rss22_project.model.orm.ItemORM;
import fr.univrouen.rss22_project.model.xml.FeedXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
@Component
public class FeedAdapter {
    @Autowired
    ItemAdapter itemAdapter;

    private FeedORM adaptToORM(FeedXML feedXML){
        return new FeedORM(feedXML.getTitle(), feedXML.getPubDate());

    }
    public Collection<ItemORM> getORMItems(FeedXML feedXML){
        FeedORM feedORM = adaptToORM(feedXML);
        return itemAdapter.adaptAllToORM(feedXML.getItems(),feedORM);
    }
}

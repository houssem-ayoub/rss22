package fr.univrouen.rss22_project.model.service;

import fr.univrouen.rss22_project.model.adapter.FeedAdapter;
import fr.univrouen.rss22_project.model.orm.FeedORM;
import fr.univrouen.rss22_project.model.orm.ItemORM;
import fr.univrouen.rss22_project.model.repository.FeedRepository;
import fr.univrouen.rss22_project.model.repository.ItemRepository;
import fr.univrouen.rss22_project.model.xml.FeedXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    FeedAdapter feedAdapter;
    @Autowired
    FeedRepository feedRepository;

    public boolean exists(FeedXML feedXML) {
        FeedORM feedORM = feedRepository.findFeedByTitleAndPubDate(feedXML.getTitle(), feedXML.getPubDate());
        return feedORM != null;
    }

    public String save(FeedXML feedXML) {

        return itemRepository.saveAll(feedAdapter.getORMItems(feedXML)).stream().reduce((first,second)->second).map(
                ItemORM::getGuid
        ).orElse(null);
    }
}

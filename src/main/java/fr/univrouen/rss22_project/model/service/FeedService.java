package fr.univrouen.rss22_project.model.service;

import fr.univrouen.rss22_project.model.adapter.FeedAdapter;
import fr.univrouen.rss22_project.model.xml.Feed;
import fr.univrouen.rss22_project.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedService {
    @Autowired
    FeedRepository feedRepository;
    @Autowired ItemService itemService;
    public void save(Feed feedXML){
        fr.univrouen.rss22_project.model.orm.Feed feedORM = FeedAdapter.adaptToORM(feedXML);
        feedRepository.save(feedORM);
        FeedAdapter.getORMItems(feedXML,feedORM).forEach(
                itemService::save
        );
    }
}

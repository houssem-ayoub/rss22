package fr.univrouen.rss22_project.model.service;

import fr.univrouen.rss22_project.model.adapter.FeedAdapter;
import fr.univrouen.rss22_project.model.repository.ItemRepository;
import fr.univrouen.rss22_project.model.xml.FeedXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedService {
    @Autowired ItemRepository itemRepository;
    @Autowired FeedAdapter feedAdapter;
    public void save(FeedXML feedXML){
        itemRepository.saveAll(feedAdapter.getORMItems(feedXML));
    }
}

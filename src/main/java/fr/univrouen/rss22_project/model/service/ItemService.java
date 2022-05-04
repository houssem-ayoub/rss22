package fr.univrouen.rss22_project.model.service;


import fr.univrouen.rss22_project.model.orm.FeedORM;
import fr.univrouen.rss22_project.model.orm.ItemORM;
import fr.univrouen.rss22_project.model.repository.FeedRepository;
import fr.univrouen.rss22_project.model.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired private FeedRepository feedRepository;
    public ItemORM findItem(String guid){
        return itemRepository.getById(guid);
    }
    @Transactional
    public List<ItemORM> list() {
        return itemRepository.findAll();
    }

    @Transactional
    public Set<ItemORM> findAllByFeed(FeedORM feedORM){
        return itemRepository.findAllByFeed(feedORM);
    }
    @Transactional
    public void delete(ItemORM itemORM){
        itemRepository.delete(itemORM);
        if(findAllByFeed(itemORM.getFeed()).isEmpty()){
            feedRepository.delete(itemORM.getFeed());
        }
    }
}

package fr.univrouen.rss22_project.model.service;


import fr.univrouen.rss22_project.model.orm.FeedORM;
import fr.univrouen.rss22_project.model.orm.ItemORM;
import fr.univrouen.rss22_project.model.repository.FeedRepository;
import fr.univrouen.rss22_project.model.repository.ItemRepository;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Expression;
import java.util.Arrays;
import java.util.List;

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
    public List<ItemORM> findAllByFeed(FeedORM feedORM){
        return itemRepository.findAllByFeed(feedORM);
    }
    @Transactional
    public List<ItemORM> findAllByWordList(String wordListPram){
        return itemRepository.findAll(wordsToSpecification(wordListPram));
    }
    @Transactional
    public List<ItemORM> findAllByYear(String year){
        return itemRepository.findAll(yearToDateTimeSpecification(year));
    }

    @Transactional
    public List<ItemORM> findAllByWordListAndYear(String words,String year){
        return itemRepository.findAll(wordsToSpecification(words).and(yearToDateTimeSpecification(year)));
    }
    @Transactional
    public void delete(ItemORM itemORM){
        itemRepository.delete(itemORM);
        if(findAllByFeed(itemORM.getFeed()).isEmpty()){
            feedRepository.delete(itemORM.getFeed());
        }
    }
    private Specification<ItemORM> wordsToSpecification(String words){
        return Arrays.stream(words.split(";")).map(this::titleContains).reduce(
                (first,second) -> first.or(second)
        ).get();
    }
    private Specification<ItemORM> titleContains(String searchWord) {
        return (root, query, builder) -> {
            Expression<String> titleLowerCase = builder.lower(root.get("title"));
            return builder.like(titleLowerCase, "%" + searchWord.toLowerCase() + "%");
        };
    }
    private Specification<ItemORM> dateEqualOrGratterSpecification(DateTime date) {
        return (root, query, builder) -> {
            Expression<DateTime> date1 = builder.treat(root.get("date"),DateTime.class);
            return builder.greaterThanOrEqualTo(date1,date);
        };
    }

    private Specification<ItemORM> yearToDateTimeSpecification(String year){
        final DateTimeFormatter df = DateTimeFormat.forPattern("yyyy");
        return dateEqualOrGratterSpecification(DateTime.parse(year,df));
    }

}

package fr.univrouen.rss22_project.model.service;


import fr.univrouen.rss22_project.model.adapter.ORMDateTimeConverter;
import fr.univrouen.rss22_project.model.orm.FeedORM;
import fr.univrouen.rss22_project.model.orm.ItemORM;
import fr.univrouen.rss22_project.model.repository.FeedRepository;
import fr.univrouen.rss22_project.model.repository.ItemRepository;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Expression;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Specification<ItemORM> titleContains(String searchWord) {
        return (root, query, builder) -> {
            Expression<String> titleLowerCase = builder.lower(root.get("title"));
            return builder.like(titleLowerCase, "%" + searchWord.toLowerCase() + "%");
        };
    }
    private Specification<ItemORM> dateEqualorgratter(DateTime date) {
        return (root, query, builder) -> {
            Expression<DateTime> date1 = builder.treat(root.get("date"),DateTime.class);
            return builder.greaterThanOrEqualTo(date1,date);
        };
    }
    @Transactional
    public List<ItemORM> findAllByFeed(FeedORM feedORM){
        return itemRepository.findAllByFeed(feedORM);
    }
    private Specification<ItemORM> wordsToList(String words){
        return Arrays.stream(words.split(";")).map(this::titleContains).reduce(
                (first,second) -> first.or(second)
        ).get();
    }
    @Transactional
    public List<ItemORM> findAllByWordList(String wordListPram){
        return itemRepository.findAll(wordsToList(wordListPram));
    }
    private Specification<ItemORM> yearToDateTime(String year){
        final DateTimeFormatter df = DateTimeFormat.forPattern("yyyy-MM-dd");
        return dateEqualorgratter(DateTime.parse(year+"-01-01",df));
    }
    @Transactional
    public List<ItemORM> findAllByYear(String year){
        return itemRepository.findAll(yearToDateTime(year));
    }

    @Transactional
    public List<ItemORM> findAllByWordListAndYear(String words,String year){
        return itemRepository.findAll(wordsToList(words).and(yearToDateTime(year)));
    }
    @Transactional
    public void delete(ItemORM itemORM){
        itemRepository.delete(itemORM);
        if(findAllByFeed(itemORM.getFeed()).isEmpty()){
            feedRepository.delete(itemORM.getFeed());
        }
    }
}

package fr.univrouen.rss22_project.model.service;


import fr.univrouen.rss22_project.model.orm.Category;
import fr.univrouen.rss22_project.model.orm.Item;
import fr.univrouen.rss22_project.repository.CategoryRepository;
import fr.univrouen.rss22_project.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    public Item findItem(String guid){
        return itemRepository.getById(guid);
    }
    @Transactional
    public List<Item> list(){
        return itemRepository.findAll();
    }
    public Item save(Item item){

        Set<Category> categorySet = categoryRepository.findAllByTermList(item.getCategories().stream().map(Category::getTerm).collect(Collectors.toList()));
        List<Category> categories = item.getCategories()
                .stream().filter(
                        category ->
                                categorySet.stream().noneMatch(
                                        category1 -> category1.getTerm().equals(category.getTerm())
                                )
                ).collect(Collectors.toList());
        categoryRepository.saveAll(categories);
        categorySet.addAll(categories);
        item.setCategories(categorySet);
        itemRepository.save(item);
        return item;
    }
}

package fr.univrouen.rss22_project.model.adapter;

import fr.univrouen.rss22_project.model.orm.CategoryORM;
import fr.univrouen.rss22_project.model.repository.CategoryRepository;
import fr.univrouen.rss22_project.model.xml.CategoryXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CategoryAdapter {
    @Autowired
    private CategoryRepository categoryRepository;
    private static final Set<CategoryORM> categoryORMSet = new HashSet<>();
    private CategoryORM getCategoryORM(CategoryXML categoryXML){
        CategoryORM categoryORM = categoryRepository.findByTerm(categoryXML.getTerm());
        if(categoryORM ==null)
            categoryORM = new CategoryORM(categoryXML.getTerm());
        return categoryORM;
    }
    private CategoryORM adaptToORM(CategoryXML categoryXML){
        for(CategoryORM categoryORM : categoryORMSet){
            if (categoryORM.getTerm().equals(categoryXML.getTerm()))
                return categoryORM;
        }
        CategoryORM categoryORM = getCategoryORM(categoryXML);
        categoryORMSet.add(categoryORM);
        return categoryORM;

    }

    public Collection<CategoryORM> adaptAllToORM(Collection<CategoryXML> categoryXMLCollection){
        return categoryXMLCollection.stream().map(this::adaptToORM).collect(Collectors.toList());
    }
    private CategoryXML adaptToXML(CategoryORM categoryORM){
        return new CategoryXML(categoryORM.getTerm());
    }
    public Collection<CategoryXML> adaptAllToXML(Collection<CategoryORM> categoryORMCollection){
        return categoryORMCollection.stream().map(this::adaptToXML).collect(Collectors.toList());
    }
}

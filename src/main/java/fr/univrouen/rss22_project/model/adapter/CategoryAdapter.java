package fr.univrouen.rss22_project.model.adapter;

import fr.univrouen.rss22_project.model.orm.Category;

public class CategoryAdapter {
    public static Category AdaptToORM(fr.univrouen.rss22_project.model.feed.Category categoryXML){
        return new Category(categoryXML.getTerm());
    }
}

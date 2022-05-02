package fr.univrouen.rss22_project.model.orm;

import fr.univrouen.rss22_project.model.resume.ItemResume;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "items" )
public class Item {
    @Id
    @GeneratedValue(generator = "UUID",strategy = GenerationType.IDENTITY)
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "guid",columnDefinition = "VARCHAR(40)", updatable = false, nullable = false)
    private final String guid = null;
    private String title;
    @ManyToMany(
            fetch = FetchType.LAZY,
            targetEntity = Category.class,
            cascade = {CascadeType.MERGE}
    )
    @JoinTable(name = "items_categories",
            joinColumns = {
            @JoinColumn(
                    name = "items_guid",
                    referencedColumnName = "guid",
                    nullable = false
            )},
            inverseJoinColumns = {
            @JoinColumn(
                    name = "categories_id",
                    referencedColumnName = "id",
                    nullable = false
            )
    })
    private Set<Category> categories;
    private String date;
    private boolean isUpdated;
    @OneToOne(targetEntity = Image.class, cascade = CascadeType.ALL)
    private Image image;
    @OneToOne(targetEntity = Content.class,cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Content content;
    @OneToMany(targetEntity = Person.class,cascade = CascadeType.ALL)
    private Set<Person> contributors;

    @ManyToOne(targetEntity = Feed.class,fetch = FetchType.LAZY,cascade = {CascadeType.MERGE})
    private Feed feed;

    public Item(){
        categories = new HashSet<>();
        contributors = new HashSet<>();
    }

    public Item(Feed feed,String title, Set<Category> categories, String date, boolean isUpdated, Image image, Content content, Set<Person> contributors) {
        this.title = title;
        this.categories = categories;
        this.date = date;
        this.isUpdated = isUpdated;
        this.image = image;
        this.content = content;
        this.contributors = contributors;
        this.feed = feed;
    }
    public String getGuid() {
        return guid;
    }

    public String getTitle() {
        return title;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public fr.univrouen.rss22_project.model.feed.Item toXMLObject(){
        fr.univrouen.rss22_project.model.feed.Image image1 = image == null ? null : image.toXMLObject();
        fr.univrouen.rss22_project.model.feed.Content content1 = content == null ? null : content.toXmlObject();
        fr.univrouen.rss22_project.model.feed.Item item = new fr.univrouen.rss22_project.model.feed.Item(guid,title,date,!isUpdated,image1,content1);
        item.addPerson(contributors.stream().map(
                Person::toXMLObject
        ).collect(Collectors.toList()));
        item.addCategories(categories.stream().map(Category::toXmlObject).collect(Collectors.toList()));
        return item;
    }
    public ItemResume toResumeXMLObject(){
        return new ItemResume(guid,title,date);
    }
}

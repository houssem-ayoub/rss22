package fr.univrouen.rss22_project.model.orm;

import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "items")
@Table(name = "items")
public class ItemORM {
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.IDENTITY)
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "item_guid", columnDefinition = "VARCHAR(40)", updatable = false, nullable = false)
    private final String guid = null;
    private String title;
    @ManyToMany(
            fetch = FetchType.LAZY,
            targetEntity = CategoryORM.class,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(name = "item_category",
            joinColumns = {
                    @JoinColumn(
                            name = "item_guid",
                            referencedColumnName = "item_guid",
                            nullable = false
                    )},
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "category_id",
                            referencedColumnName = "category_id",
                            nullable = false
                    )
            })
    private Set<CategoryORM> categories;
    @Column(columnDefinition = "DATETIME(3)")
    private DateTime date;
    private boolean isUpdated;
    @OneToOne(targetEntity = ImageORM.class, cascade = CascadeType.ALL)
    private ImageORM image;
    @OneToOne(targetEntity = ContentORM.class, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private ContentORM content;
    @OneToMany(targetEntity = PersonORM.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "item_contributor",
            joinColumns = {
                    @JoinColumn(
                            name = "item_guid",
                            referencedColumnName = "item_guid",
                            nullable = false
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "contributor_id",
                            referencedColumnName = "contributor_id",
                            nullable = false
                    )
            }
    )
    private Set<PersonORM> contributors;

    @ManyToOne(targetEntity = FeedORM.class, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private FeedORM feed;

    public ItemORM() {
        categories = new HashSet<>();
        contributors = new HashSet<>();
    }

    public ItemORM(FeedORM feed, String title, DateTime date, boolean isUpdated, ImageORM image, ContentORM content) {
        this();
        this.title = title;
        this.date = date;
        this.isUpdated = isUpdated;
        this.image = image;
        this.content = content;
        this.feed = feed;
    }

    public String getGuid() {
        return guid;
    }

    public String getTitle() {
        return title;
    }

    public DateTime getDate() {
        return date;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public ImageORM getImage() {
        return image;
    }

    public ContentORM getContent() {
        return content;
    }

    public Set<PersonORM> getContributors() {
        return contributors;
    }

    public Set<CategoryORM> getCategories() {
        return categories;
    }

    public FeedORM getFeed() {
        return feed;
    }

    public void setCategoryCollection(Collection<CategoryORM> categoryORMCollection) {
        this.categories = Set.copyOf(categoryORMCollection);
    }

    public void setPersonCollection(Collection<PersonORM> personORMCollection) {
        this.contributors = Set.copyOf(personORMCollection);
    }
}

package fr.univrouen.rss22_project.model.orm;

import javax.persistence.*;

@Entity(name = "contributors")
@Table(name = "contributors")
public class PersonORM {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contributor_id")
    private Long id;
    private boolean isAuthor;
    @Column(nullable = false)
    private String name = null;
    private String email= null;
    @OneToOne(targetEntity = LinkORM.class,cascade = CascadeType.ALL)
    private LinkORM uri = null;

    public PersonORM() {
    }

    public PersonORM(boolean isAuthor, String name) {
        this.isAuthor = isAuthor;
        this.name = name;
    }

    public PersonORM(boolean isAuthor, String name, String email, LinkORM uri) {
        this.isAuthor = isAuthor;
        this.name = name;
        this.email = email;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public boolean isAuthor() {
        return isAuthor;
    }

    public String getEmail() {
        return email;
    }

    public LinkORM getUri() {
        return uri;
    }
}

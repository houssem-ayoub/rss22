package fr.univrouen.rss22_project.model.orm;

import fr.univrouen.rss22_project.model.feed.Author;
import fr.univrouen.rss22_project.model.feed.Contributor;

import javax.persistence.*;

@Entity(name = "contributors")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean isAuthor;
    @Column(nullable = false)
    private String name = null;
    private String email= null;
    @OneToOne(targetEntity = Link.class,cascade = CascadeType.ALL)
    private Link uri = null;

    public Person() {
    }

    public Person(boolean isAuthor, String name) {
        this.isAuthor = isAuthor;
        this.name = name;
    }

    public Person(boolean isAuthor, String name, String email, Link uri) {
        this.isAuthor = isAuthor;
        this.name = name;
        this.email = email;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    fr.univrouen.rss22_project.model.feed.Person toXMLObject(){
        fr.univrouen.rss22_project.model.feed.Link link = uri == null? null : uri.toXMLObject();
        if(isAuthor){
            return new Author(name,email,link);
        }
        return new Contributor(name,email,link);
    }
}

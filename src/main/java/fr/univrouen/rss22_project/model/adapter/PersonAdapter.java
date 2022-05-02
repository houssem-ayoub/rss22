package fr.univrouen.rss22_project.model.adapter;

import fr.univrouen.rss22_project.model.feed.Author;
import fr.univrouen.rss22_project.model.feed.LinkRel;
import fr.univrouen.rss22_project.model.orm.Link;
import fr.univrouen.rss22_project.model.orm.Person;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonAdapter {
    public static Person adaptToORM(fr.univrouen.rss22_project.model.feed.Person personXML){
        return new Person(
                personXML instanceof Author,
                personXML.getName(),
                personXML.getEmail(),
                personXML.getUri() ==null ? null : new Link(
                        personXML.getUri().getRel()== LinkRel.SELF?"SELF":"ALTERNATE",
                        personXML.getUri().getType(),
                        personXML.getUri().getHref()
                )
                );
        //return new Person(true,"hello");
    }
    public static Set<Person> adaptAllToORM(Set<fr.univrouen.rss22_project.model.feed.Person> personXMLSet){
        return personXMLSet.stream().map(PersonAdapter::adaptToORM).collect(Collectors.toSet());
    }
}

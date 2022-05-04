package fr.univrouen.rss22_project.model.adapter;

import fr.univrouen.rss22_project.model.orm.PersonORM;
import fr.univrouen.rss22_project.model.xml.AuthorXML;
import fr.univrouen.rss22_project.model.xml.ContributorXML;
import fr.univrouen.rss22_project.model.xml.PersonXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;
@Component
public class PersonAdapter {
    @Autowired private LinkAdapter linkAdapter;
    private PersonORM adaptToORM(PersonXML personXML){
        return new PersonORM(
                personXML instanceof AuthorXML,
                personXML.getName(),
                personXML.getEmail(),
                linkAdapter.adaptToORM(personXML.getUri())
                );
    }
    public Collection<PersonORM> adaptAllToORM(Collection<PersonXML> personXMLCollection){
        return personXMLCollection.stream().map(this::adaptToORM).collect(Collectors.toList());
    }
    private PersonXML adaptToXML(PersonORM personORM){
        if(personORM.isAuthor()){
            return new AuthorXML(
                    personORM.getName(),
                    personORM.getEmail(),
                    linkAdapter.adaptToXML(personORM.getUri())
            );
        }
        return new ContributorXML(
                personORM.getName(),
                personORM.getEmail(),
                linkAdapter.adaptToXML(personORM.getUri())
        );
    }

    public Collection<PersonXML> adaptAllToXML(Collection<PersonORM> personORMCollection){
        return personORMCollection.stream().map(this::adaptToXML).collect(Collectors.toList());
    }
}

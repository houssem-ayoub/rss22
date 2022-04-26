package fr.univrouen.rss22_project.controller;

import fr.univrouen.rss22_project.model.feed.Author;
import fr.univrouen.rss22_project.model.feed.Contributor;
import fr.univrouen.rss22_project.model.feed.Item;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import fr.univrouen.rss22_project.model.resume.ItemResume;

import java.util.List;

@RestController
public class RSSController {

   @GetMapping(value = "/rss22/resume/xml",produces = MediaType.APPLICATION_XML_VALUE)
    public ItemResume getResume(){

       return new ItemResume("1111111","item","24/12/2012");
    }
    @GetMapping(value = "/rss22/resume/xml/{guid}",produces = MediaType.APPLICATION_XML_VALUE)
    public Item getDetails(@PathVariable("guid") String guid){
        Item item = new Item(guid,"test","25/02/2012");
        item.addPerson(List.of(
                new Author("Cassandre LAVAZZA"),
                new Contributor("Bondi RECARD")
        ));
        return item;
    }
    @PostMapping(value = "rss22/insert",produces = MediaType.APPLICATION_XML_VALUE)
    public String insertData(@RequestBody String data){
        return "<result>" +
                "<guid>5555555</guid>" +
                "<status>INSERTED</status>" +
                "</result>";
    }
    @DeleteMapping(value = "/rss22/delete/{guid}",produces = MediaType.APPLICATION_XML_VALUE)
    public String deleteData(@PathVariable("guid") String guid){
        return "<result>" +
                "<id>5555555</id>" +
                "<status>DELETED</status>" +
                "</result>";
    }
}
